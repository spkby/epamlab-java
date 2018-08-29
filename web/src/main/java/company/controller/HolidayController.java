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

    private static AccountDAO accountDAO = new AccountDAO();
    private static HolidayDAO holidayDAO = new HolidayDAO();
    private static StatusDAO statusDAO = new StatusDAO();

    @GetMapping(SLASH + HOLIDAY)
    public String root() {
        return REDIRECT + SLASH + HOLIDAY + SLASH + LIST;
    }

    @GetMapping(SLASH + HOLIDAY + SLASH + LIST)
    public String list(@CookieValue(value = LOGIN, defaultValue = NO_SPACE) String login, Model model) {

        model = accountForJSP(login, model);

        model.addAttribute(LIST, true);

        switch (Security.getRoleId(login)) {
            case LEAD:
                model.addAttribute(HOLIDAYS, holidayDAO.getListByDepartment(accountDAO.getAccountByLogin(login).getEmployee().getDepartment()));
                break;
            case MANAGER:
                model.addAttribute(HOLIDAYS, holidayDAO.getList());
                break;
            default:
                return REDIRECT + SLASH + HOLIDAY + SLASH + EMPLOYEE + SLASH + accountDAO.getAccountByLogin(login).getEmployee().getId();
        }
        return HOLIDAY + SLASH + LIST;
    }

    @GetMapping(SLASH + HOLIDAY + SLASH + EMPLOYEE + SLASH + _ID)
    public String employee(@CookieValue(value = LOGIN, defaultValue = "") String login,
                           @PathVariable(value = ID) int id, Model model) {

        model = accountForJSP(login, model);

       /* if (!Security.checkLoginToEmployeeId(login, id)) {
            return "redirect:/login";
        }*/

        model.addAttribute(ACCOUNT, accountDAO.getById(id));

        model.addAttribute(HOLIDAYS, holidayDAO.getListByEmployee(new EmployeeDAO().getById(id)));

        return HOLIDAY + SLASH + LIST;
    }

    @GetMapping(HOLIDAY + SLASH + VIEW + SLASH)
    public String view(@CookieValue(value = LOGIN, defaultValue = NO_SPACE) String login) {

        return REDIRECT + SLASH + HOLIDAY + SLASH + EMPLOYEE + SLASH + accountDAO.getAccountByLogin(login).getEmployee().getId();
    }

    @GetMapping(SLASH + HOLIDAY + SLASH + VIEW + SLASH + _ID)
    public String viewById(@CookieValue(value = LOGIN, defaultValue = NO_SPACE) String login,
                           @PathVariable(value = ID) int id, Model model) {
        model = accountForJSP(login, model);

/*        if (!Security.checkLoginToHolidayIdToView(login, id)) {
            return "redirect:/login";
        }*/

        model.addAttribute(HOLIDAY, holidayDAO.getById(id));
        return HOLIDAY + SLASH + VIEW;
    }

    @GetMapping(SLASH + HOLIDAY + SLASH + ADD)
    public String getAdd(@CookieValue(value = LOGIN, defaultValue = NO_SPACE) String login, Model model) {

        model = accountForJSP(login, model);

        return SLASH + HOLIDAY + SLASH + ADD;
    }

    @PostMapping(SLASH + HOLIDAY + SLASH + ADD)
    public String addEmployee(@CookieValue(value = LOGIN, defaultValue = "") String login,
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

            if (Utils.date2IsMoreThanDate1(dateFrom, dateTo)) {
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
            return REDIRECT + SLASH + HOLIDAY + SLASH + LIST;
        } else {
            return REDIRECT + SLASH + HOLIDAY + SLASH + ADD + "?" + ERROR + "=" + errorMsg.toString();
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
                               @RequestParam(value = HOLIDAY + DASH + ID) String id) {

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

            if (Utils.date2IsMoreThanDate1(dateFrom, dateTo)) {
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
            errorMsg.append(e.getMessage()).append("\n");
        }

        if (!isError) {
            holidayDAO.update(holiday);

            return "redirect:/holiday/view/" + holiday.getId();
        } else {
            return "redirect:/holiday/edit/" + holiday.getId() + "?" + ERROR + "=" + errorMsg.toString();
        }
    }

    @GetMapping("/holiday/delete")
    public String getDelete(@CookieValue(value = "login", defaultValue = "") String login, Model model) {

        model = accountForJSP(login, model);

        return "error";
    }

    @PostMapping("/holiday/accepted")
    public String accepted(@CookieValue(value = "login", defaultValue = "") String login,
                           @RequestParam(value = "holiday_id") String id) {

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
                         @RequestParam(value = HOLIDAY + DASH + ID) String id) {

        return changeStatus(Integer.parseInt(id), new StatusDAO().getById(3), login);
    }

    @PostMapping("/holiday/delete")
    public String add(@CookieValue(value = LOGIN, defaultValue = NO_SPACE) String login,
                      @RequestParam(value = HOLIDAY + DASH + ID) String id) {

        int holidayId = Integer.parseInt(id);

        if (!Security.checkLoginToHolidayId(login, holidayId)) {
            return REDIRECT + SLASH + LOGIN;
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
            return "redirect:/holiday/edit/" + holidayId + "?" + ERROR + "=" + errorMsg.toString();
        }
    }

}
