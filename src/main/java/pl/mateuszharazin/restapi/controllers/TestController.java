package pl.mateuszharazin.restapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.mateuszharazin.restapi.model.Test;
import pl.mateuszharazin.restapi.repository.TestRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
public class TestController {

    @Autowired
    TestRepository testRepository;

    @RequestMapping(value = "/admin/test/create", method = RequestMethod.GET)
    public ModelAndView test() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("test", new Test());
        modelAndView.setViewName("testCreate");

        return modelAndView;
    }

    @RequestMapping(value = "/admin/test/create", method = RequestMethod.POST)
    public ModelAndView createTest(@Valid@ModelAttribute Test test, BindingResult bindingResult, ModelMap modelMap) {

        ModelAndView modelAndView = new ModelAndView();
        if(bindingResult.hasErrors()) {
            modelAndView.addObject("successMessage", "Please correct the errors in form!");
            modelMap.addAttribute("bindingResult", bindingResult);
        }
        else {
            testRepository.save(test);
            modelAndView.addObject("successMessage", "Test has been added successfully!");
        }
        modelAndView.addObject("test", new Test());

        modelAndView.setViewName("tests");


        return modelAndView;
    }

    @RequestMapping(value = "/admin/test/list", method = RequestMethod.GET)
    public ModelAndView listTests() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("tests", testRepository.findAll());
        modelAndView.setViewName("testList");

        return modelAndView;
    }

}
