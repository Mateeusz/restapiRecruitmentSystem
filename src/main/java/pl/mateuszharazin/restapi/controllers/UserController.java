package pl.mateuszharazin.restapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.mateuszharazin.restapi.model.User;
import pl.mateuszharazin.restapi.repository.UserRepository;

import javax.validation.Valid;

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

    @RequestMapping(value = "/home/user/edit", method = RequestMethod.GET)
    public ModelAndView updateUser(Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", userRepository.findByEmail(authentication.getName()));
        modelAndView.setViewName("userEdit");

        return modelAndView;
    }

    @RequestMapping(value = "/home/user/edit", method = RequestMethod.POST)
    public ModelAndView putUser(Authentication authentication, @ModelAttribute User user, BindingResult bindingResult, ModelMap modelMap) {

        ModelAndView modelAndView = new ModelAndView();
        User user1 = userRepository.findByEmail(authentication.getName());
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setPhoneNumber(user.getPhoneNumber());

        if(bindingResult.hasErrors()) {
            modelAndView.addObject("successMessage", "Please correct the errors in form!");
            modelMap.addAttribute("bindingResult", bindingResult);
        }
        else {
            userRepository.save(user1);
            modelAndView.addObject("successMessage", "Data has been updated successfully!");
        }
        modelAndView.addObject("user", user);
        modelAndView.setViewName("userEdit");
        return modelAndView;
    }
}
