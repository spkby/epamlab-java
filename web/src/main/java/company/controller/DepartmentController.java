package company.controller;

import company.DAO.AccountDAO;
import company.DAO.DepartmentDAO;
import company.DAO.EmployeeDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static company.Constants.*;

@Controller
public class DepartmentController extends AbstractController {

    @GetMapping(SLASH + DEPARTMENT)
    public String root() {
        return REDIRECT + SLASH + DEPARTMENT + SLASH + LIST;
    }

    @GetMapping(SLASH + DEPARTMENT + SLASH + LIST)
    public String list(@CookieValue(value = LOGIN, defaultValue = NO_SPACE) String login, Model model) {

        model = accountForJSP(login, model);

        model.addAttribute(DEPARTMENTS, new DepartmentDAO().getList());
        return DEPARTMENT + SLASH + LIST;
    }

    @GetMapping(SLASH + DEPARTMENT + SLASH + VIEW)
    public String view_() {
        return REDIRECT + SLASH + DEPARTMENT + SLASH + VIEW + SLASH;
    }

    @GetMapping(SLASH + DEPARTMENT + SLASH + VIEW + SLASH)
    public String view(@CookieValue(value = LOGIN, defaultValue = NO_SPACE) String login, Model model) {
        return REDIRECT + SLASH + DEPARTMENT + SLASH + VIEW + SLASH + new AccountDAO().getAccountByLogin(login).getEmployee().getDepartment().getId();
    }

    @GetMapping(SLASH + DEPARTMENT + SLASH + VIEW + SLASH + _ID)
    public String viewById(@CookieValue(value = LOGIN, defaultValue = NO_SPACE) String login, @PathVariable(value = ID) int id, Model model) {

        model = accountForJSP(login, model);

        model.addAttribute(EMPLOYEES, new EmployeeDAO().getListByDepartment(id));
        return SLASH + EMPLOYEE + SLASH + LIST;
    }
}
