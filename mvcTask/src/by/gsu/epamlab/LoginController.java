package by.gsu.epamlab;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.gsu.epamlab.beans.Role;
import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.impl.UserImpl;

public class LoginController extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		performTask(request, response);
	}

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// to retrieve the form data
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		// to check out the form data
		if (login == null || password == null) {
			forwardError("Login or password is missing.", request, response);
			return;
		}
		// to rectify and to check out the form data once more
		login = login.trim();
		password = password.trim();
		if ("".equals(login) || "".equals(password)) {
			forwardError("Login or password is empty.", request, response);
			return;
		}
		// to create an instance of the business logic class
		UserImpl userImpl = new UserImpl();
		// to call the method and to get the result
		User user = userImpl.getUser(login, password);
		if(user.getRole() != Role.FAKER) {
			// main workflow
			// to put the result into the request scope
			request.setAttribute("user", user);
			// to forward to a view layer
			forward("/main.jsp", request, response);
		} else {
			// alternative workflow
			forwardError("Wrong password.", request, response);
		}
	}

	// to forward to a view layer
	protected void forward(String url, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

	// to forward back with an error message 
	protected void forwardError(String message, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// to put a message into the request scope
		request.setAttribute("errorMessage", message);
		forward("/index.jsp", request, response);
	}
}
