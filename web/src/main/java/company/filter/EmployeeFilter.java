package company.filter;

import javax.servlet.annotation.WebFilter;

@WebFilter("/employee/*")
public class EmployeeFilter extends AbstractFilter {

    @Override
    protected boolean isLevelOK(String login, String path) {
        return true;
    }
}
