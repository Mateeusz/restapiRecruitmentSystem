package pl.mateuszharazin.restapi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("Home")
    public String Home()
    {
        return "/Views/home.jsp";
    }
}
