package company.controller;

import company.DAO.AccountDAO;
import company.DAO.DepartmentDAO;
import company.DAO.EmployeeDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DepartmentController extends AbstractController {

    @GetMapping("/department")
    public String root() {
        return "redirect:/department/list";
    }

    @GetMapping("/department/list")
    public String list(@CookieValue(value = "login", defaultValue = "") String login, Model model) {

        model = accountForJSP(login, model);

        model.addAttribute("departments", new DepartmentDAO().getList());
        return "department/list";
    }

    @GetMapping("/department/view")
    public String view_() {
        return "redirect:/department/view/";
    }

    @GetMapping("/department/view/")
    public String view(@CookieValue(value = "login", defaultValue = "") String login, Model model) {

        return "redirect:/department/view/" + new AccountDAO().getAccountByLogin(login).getEmployee().getDepartment().getId();
    }

    @GetMapping("/department/view/{id}")
    public String viewById(@CookieValue(value = "login", defaultValue = "") String login, @PathVariable(value = "id") int id, Model model) {

        model = accountForJSP(login, model);

        model.addAttribute("employees", new EmployeeDAO().getListByDepartment(id));
        return "employee/list";
    }
}
