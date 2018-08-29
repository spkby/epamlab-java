package company.filter;

import company.Security;

import javax.servlet.annotation.WebFilter;

import static company.Constants.*;

@WebFilter("/holiday/*")
public class HolidayFilter extends AbstractFilter {

    private static final String EMPLOYEE = "employee";
    private static final String VIEW = "view";

    @Override
    protected boolean isLevelOK(String login, String path) {

        boolean isOK = false;

        String[] array = path.split(SLASH);

        if (array.length == 2) return true;

        switch (array[2]) {

            case EMPLOYEE:
                if ((array.length > 3)) {
                    isOK = Security.checkLoginToEmployeeId(login, Integer.parseInt(array[3]));
                } else {
                    isOK = true;
                }
                break;
            case VIEW:
                if ((array.length > 3)) {
                    isOK = Security.checkLoginToHolidayIdToView(login, Integer.parseInt(array[3]));
                } else {
                    isOK = true;
                }
                break;
            case EDIT:
            case DELETE:
                if ((array.length > 3)) {
                    isOK = Security.checkLoginToHolidayId(login, Integer.parseInt(array[3]));
                } else {
                    isOK = true;
                }
                break;
            default:
                isOK = true;
        }
        return isOK || Security.getRoleId(login) == Security.RoleId.MANAGER;
    }
}
