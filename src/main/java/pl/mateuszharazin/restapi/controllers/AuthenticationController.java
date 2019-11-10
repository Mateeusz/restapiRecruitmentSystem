package pl.mateuszharazin.restapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.mateuszharazin.restapi.model.User;
import pl.mateuszharazin.restapi.repository.UserRepository;
import pl.mateuszharazin.restapi.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class AuthenticationController {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = { "/login" }, method = RequestMethod.GET)
    public ModelAndView login() {
        System.out.println("Był");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login"); // resources/template/login.html
        return modelAndView;
    }

//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public ModelAndView sendCredentials(HttpServletResponse httpServletResponse, @ModelAttribute User user) {
//        System.out.println("NIE BYŁ");
//        ModelAndView modelAndView = new ModelAndView();
//        System.out.println(user.getEmail());
//        Cookie cookie = new Cookie("email", user.getEmail());
//        httpServletResponse.addCookie(cookie);
//        return modelAndView;
//    }



    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register"); // resources/template/register.html
        return modelAndView;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home(Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", userRepository.findByEmail(authentication.getName()));
        modelAndView.setViewName("home"); // resources/template/home.html
        return modelAndView;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView adminHome(Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", userRepository.findByEmail(authentication.getName()));
        modelAndView.setViewName("admin"); // resources/template/admin.html
        return modelAndView;
    }

    @RequestMapping(value="/register", method=RequestMethod.POST)
    public ModelAndView registerUser(@Valid User user, BindingResult bindingResult, ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView();
        // Check for the validations
        if(bindingResult.hasErrors()) {
            modelAndView.addObject("successMessage", "Please correct the errors in form!");
            modelMap.addAttribute("bindingResult", bindingResult);
        }
        else if(userService.isUserAlreadyPresent(user)){
            modelAndView.addObject("successMessage", "user already exists!");
        }
        // we will save the user if, no binding errors
        else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User is registered successfully!");
        }
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("register");
        return modelAndView;
    }
}
