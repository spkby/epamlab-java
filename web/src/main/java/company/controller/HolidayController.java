package company.controller;

import company.DAO.AccountDAO;
import company.DAO.EmployeeDAO;
import company.DAO.HolidayDAO;
import company.DAO.StatusDAO;
import company.Security;
import company.Utils;
import company.model.Holiday;
import company.model.Status;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static company.Constants.*;


@Controller
public class HolidayController extends AbstractController {

    private static final AccountDAO accountDAO = new AccountDAO();
    private static final HolidayDAO holidayDAO = new HolidayDAO();
    private static final StatusDAO statusDAO = new StatusDAO();

    @GetMapping("/holiday")
    public String root() {
        return "redirect:/holiday/list";
    }

    @GetMapping("/holiday/list")
    public String list(@CookieValue(value = LOGIN, defaultValue = NO_SPACE) String login, Model model) {

        model = accountForJSP(login, model);

        model.addAttribute("list", true);

        switch (Security.getRoleId(login)) {
            case LEAD:
                model.addAttribute(HOLIDAYS, holidayDAO.getListByDepartment(accountDAO.getAccountByLogin(login).getEmployee().getDepartment()));
                break;
            case MANAGER:
                model.addAttribute(HOLIDAYS, holidayDAO.getList());
                break;
            default:
                return "redirect:/holiday/employee/" + accountDAO.getAccountByLogin(login).getEmployee().getId();
        }
        return "holiday/list";
    }

    @GetMapping("/holiday/employee/{id}")
    public String employee(@CookieValue(value = LOGIN, defaultValue = NO_SPACE) String login,
                           @PathVariable(value = ID) int id, Model model) {

        model = accountForJSP(login, model);

       /* if (!Security.checkLoginToEmployeeId(login, id)) {
            return "redirect:/login";
        }*/

        model.addAttribute(ACCOUNT, accountDAO.getById(id));

        model.addAttribute(HOLIDAYS, holidayDAO.getListByEmployee(new EmployeeDAO().getById(id)));

        return "holiday/list";
    }

    @GetMapping("/holiday/employee")
    public String employee_() {
        return "redirect:/holiday/list";
    }

    @GetMapping("/holiday/view")
    public String view_() {
        return "redirect:/holiday/view/";
    }

    @GetMapping("/holiday/view/")
    public String view(@CookieValue(value = LOGIN, defaultValue = NO_SPACE) String login) {

        return "redirect:/holiday/employee/" + accountDAO.getAccountByLogin(login).getEmployee().getId();
    }

    @GetMapping("/holiday/view/{id}")
    public String viewById(@CookieValue(value = LOGIN, defaultValue = NO_SPACE) String login,
                           @PathVariable(value = ID) int id, Model model) {
        model = accountForJSP(login, model);

/*        if (!Security.checkLoginToHolidayIdToView(login, id)) {
            return "redirect:/login";
        }*/

        model.addAttribute(HOLIDAY, holidayDAO.getById(id));
        return "holiday/view";
    }

    @GetMapping("/holiday/add")
    public String getAdd(@CookieValue(value = LOGIN, defaultValue = NO_SPACE) String login, Model model) {

        model = accountForJSP(login, model);

        return "holiday/add";
    }

    @PostMapping("/holiday/add")
    public String addEmployee(@CookieValue(value = LOGIN, defaultValue = NO_SPACE) String login,
                              @RequestParam(value = DATE_FROM) String dateFrom,
                              @RequestParam(value = DATE_TO) String dateTo) {

        boolean isError = false;
        StringBuilder errorMsg = new StringBuilder();


        Holiday holiday = new Holiday();
        try {

            if (Utils.dateIsMoreThanToday(dateFrom)) {
                isError = true;
                errorMsg.append(DATE_FROM_BEFORE_TODAY);
            }

            if (Utils.dateTwoIsMoreThanDateOne(dateFrom, dateTo)) {
                isError = true;
                if (errorMsg.length() == 0) {
                    errorMsg.append(SPACE);
                }
                errorMsg.append(DATE_TO_BEFORE_FROM);
            }

            if (!isError) {
                holiday.setDateFrom(Utils.getDate(dateFrom));
                holiday.setDateTo(Utils.getDate(dateTo));
                holiday.setStatus(statusDAO.getById(1));
                holiday.setEmployee(accountDAO.getAccountByLogin(login).getEmployee());
            }

        } catch (IllegalArgumentException e) {
            isError = true;
            errorMsg.append(e.getMessage()).append(NEXT_STRING);
        }

        if (!isError) {
            holidayDAO.add(holiday);
            return "redirect:/holiday/list";
        } else {
            return "redirect:/holiday/add?error=" + errorMsg.toString();
        }
    }

    @GetMapping("/holiday/edit/{id}")
    public String getEdit(@CookieValue(value = LOGIN, defaultValue = NO_SPACE) String login,
                          @PathVariable(value = ID) int id, Model model) {

        model = accountForJSP(login, model);

/*        if (!Security.checkLoginToHolidayId(login, id)) {
            return "redirect:/login";
        }*/

        model.addAttribute(HOLIDAY, holidayDAO.getById(id));

        return "/holiday/edit";
    }

    @PostMapping("/holiday/edit")
    public String editEmployee(@CookieValue(value = LOGIN, defaultValue = NO_SPACE) String login,
                               @RequestParam(value = DATE_FROM) String dateFrom,
                               @RequestParam(value = DATE_TO) String dateTo,
                               @RequestParam(value = HOLIDAY_ID) String id) {

        int holidayId = Integer.parseInt(id);
/*
        if (!Security.checkLoginToHolidayId(login, holidayId)) {
            return "redirect:/login";
        }*/

        Holiday holiday = null;
        boolean isError = false;
        StringBuilder errorMsg = new StringBuilder();

        try {
            if (Utils.dateIsMoreThanToday(dateFrom)) {
                isError = true;
                errorMsg.append(DATE_FROM_BEFORE_TODAY);
            }

            if (Utils.dateTwoIsMoreThanDateOne(dateFrom, dateTo)) {
                isError = true;
                if (errorMsg.length() == 0) {
                    errorMsg.append(SPACE);
                }
                errorMsg.append(DATE_TO_BEFORE_FROM);
            }

            holiday = holidayDAO.getById(holidayId);
            holiday.setDateFrom(Utils.getDate(dateFrom));
            holiday.setDateTo(Utils.getDate(dateTo));

        } catch (IllegalArgumentException e) {
            isError = true;
            errorMsg.append(e.getMessage()).append(NEXT_STRING);
        }

        if (!isError) {
            holidayDAO.update(holiday);

            return "redirect:/holiday/view/" + holiday.getId();
        } else {
            return "redirect:/holiday/edit/" + holiday.getId() + "?error=" + errorMsg.toString();
        }
    }

    @GetMapping("/holiday/delete")
    public String getDelete(@CookieValue(value = LOGIN, defaultValue = NO_SPACE) String login, Model model) {

        model = accountForJSP(login, model);

        return "error";
    }

    @PostMapping("/holiday/accepted")
    public String accepted(@CookieValue(value = LOGIN, defaultValue = NO_SPACE) String login,
                           @RequestParam(value = HOLIDAY_ID) String id) {

        return changeStatus(Integer.parseInt(id), new StatusDAO().getById(2), login);
    }

    private static String changeStatus(int id, Status status, String login) {

/*        if (!(Security.getRoleId(login) == Security.RoleId.MANAGER || Security.getRoleId(login) == Security.RoleId.LEAD)) {
            return "redirect:/login";
        }*/

        Holiday holiday = holidayDAO.getById(id);
        holiday.setStatus(status);

        holidayDAO.update(holiday);

        return "redirect:/holiday/view/" + id;
    }

    @PostMapping("/holiday/denied")
    public String denied(@CookieValue(value = LOGIN, defaultValue = NO_SPACE) String login,
                         @RequestParam(value = HOLIDAY_ID) String id) {

        return changeStatus(Integer.parseInt(id), new StatusDAO().getById(3), login);
    }

    @PostMapping("/holiday/delete")
    public String add(@CookieValue(value = LOGIN, defaultValue = NO_SPACE) String login,
                      @RequestParam(value = HOLIDAY_ID) String id) {

        int holidayId = Integer.parseInt(id);

        if (!Security.checkLoginToHolidayId(login, holidayId)) {
            return "redirect:/login";
        }

        boolean isError = false;
        StringBuilder errorMsg = new StringBuilder();

        try {
            holidayDAO.remove(holidayDAO.getById(holidayId).getId());
        } catch (Exception e) {
            isError = true;
            errorMsg.append(e.getMessage()).append(NEXT_STRING);
        }

        if (!isError) {
            return "redirect:/holiday/view/" + holidayId;
        } else {
            return "redirect:/holiday/edit/" + holidayId + "?error=" + errorMsg.toString();
        }
    }

}
