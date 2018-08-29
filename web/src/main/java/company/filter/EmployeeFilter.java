package company.filter;

import javax.servlet.annotation.WebFilter;

import static company.Constants.*;

@WebFilter(SLASH + EMPLOYEE + SLASH + ASTERISK)
public class EmployeeFilter extends AbstractFilter {

    @Override
    protected boolean isLevelOK(String login, String path) {
        return true;
    }
}
