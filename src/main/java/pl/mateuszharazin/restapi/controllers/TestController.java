package pl.mateuszharazin.restapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.mateuszharazin.restapi.model.Question;
import pl.mateuszharazin.restapi.model.Test;
import pl.mateuszharazin.restapi.repository.QuestionRepository;
import pl.mateuszharazin.restapi.repository.TestRepository;
import pl.mateuszharazin.restapi.repository.TestTypeRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
public class TestController {

    @Autowired
    TestRepository testRepository;
    @Autowired
    TestTypeRepository testTypeRepository;
    @Autowired
    QuestionRepository questionRepository;

    @RequestMapping(value = "/admin/test/create", method = RequestMethod.GET)
    public ModelAndView test() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("test", new Test());
        modelAndView.addObject("testTypes", testTypeRepository.findAll());
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

        modelAndView.setViewName("testList");


        return modelAndView;
    }

    @RequestMapping(value = "/admin/test/list", method = RequestMethod.GET)
    public ModelAndView listTests() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("tests", testRepository.findAll());
        modelAndView.setViewName("testList");

        return modelAndView;
    }

//    @RequestMapping(value = "/admin/test/{id}", method = RequestMethod.GET)
//    public ModelAndView getTest(@PathVariable(name = "id") int id) {
//        ModelAndView modelAndView = new ModelAndView();
////        Test test = testRepository.findById(id);
////        Question questions = questionRepository.findAllById(id);
//        modelAndView.addObject("test", testRepository.findAllById(id));
////        modelAndView.addObject("questions", questions);
//        modelAndView.setViewName("testGet");
//
//        return modelAndView;
//    }


    @RequestMapping(value = "/admin/test/get/{id}", method = RequestMethod.GET)
    public ModelAndView getTestById(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("test", testRepository.findById(id));
        modelAndView.setViewName("testGet");
        return modelAndView;
    }
}
