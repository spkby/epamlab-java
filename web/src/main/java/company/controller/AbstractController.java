package company.controller;

import company.DAO.AccountDAO;
import org.springframework.ui.Model;
import static company.Constants.*;

public abstract class AbstractController {

    protected static Model accountForJSP(String login, Model model) {

        if (!login.isEmpty()) {
            model.addAttribute(CURRENT_ACCOUNT, new AccountDAO().getAccountByLogin(login));
        }

        return model;
    }
}
