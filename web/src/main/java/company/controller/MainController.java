package company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static company.Constants.*;

@Controller
public class MainController extends AbstractController{

    @GetMapping(SLASH)
    public String index(@CookieValue(value = LOGIN, defaultValue = NO_SPACE) String login, Model model) {

        model = accountForJSP(login, model);

        return INDEX;
    }
}
