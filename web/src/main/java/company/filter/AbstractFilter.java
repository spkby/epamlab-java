package company.filter;

import company.DAO.AccountDAO;
import company.model.Account;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractFilter extends HttpFilter {

    protected abstract boolean isLevelOK(String login, String path);

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        boolean isLogged = false;
        Account account = null;
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("login")) {
                    account = new AccountDAO().getAccountByLogin(cookie.getValue());
                    if (account != null) {
                        isLogged = true;
                        break;
                    }
                }
            }
        }

        if (isLogged && isLevelOK(account.getLogin(), req.getRequestURI())) {
            chain.doFilter(req, res);
        } else {
            res.setStatus(401);
            res.getWriter().println("Forbidden");
            req.getRequestDispatcher("/login").forward(req, res);
        }
    }

}
