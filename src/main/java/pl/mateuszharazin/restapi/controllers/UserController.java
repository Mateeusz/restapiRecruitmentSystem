package pl.mateuszharazin.restapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.mateuszharazin.restapi.repository.UserRepository;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;


    @RequestMapping(value = "/home/user/get", method = RequestMethod.GET)
    public ModelAndView getUser(Authentication authentication) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", userRepository.findByEmail(authentication.getName()));
        modelAndView.setViewName("userDetails");

        return modelAndView;
    }
}
