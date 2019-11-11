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

    @RequestMapping(value = "/admin/test/{id}/addQuestion", method = RequestMethod.GET)
    public ModelAndView editTest(@PathVariable("id") int id) {

        ModelAndView modelAndView = new ModelAndView();
        List<Question> questionsIn = questionRepository.findAllByTests(id);
        List<Question> allQuestions = questionRepository.findAll();
        Test test = testRepository.findAllById(id);

        modelAndView.addObject("questionsIn", questionsIn);
        modelAndView.addObject("allQuestions", allQuestions);
        modelAndView.addObject("test", test);
        modelAndView.addObject("question", new Question());

        modelAndView.setViewName("testAddQuestion");
        return modelAndView;
    }

//    FIXME: insertQuestion not work as should. Dodaje pytanie do testu ale rzuca błędem plus
//    FIXME: check also testRepo to fix SQL Query there's some mistakes ALSO DELETE MODEL
//    FIXME: HOW TO USE BETWEEN TABLE ??? 
    
    @RequestMapping(value = "/admin/test/{id}/addQuestion/{id2}", method = RequestMethod.POST)
    public ModelAndView addQuestion(@PathVariable("id") int id, @PathVariable("id2") int questionId) {

        ModelAndView modelAndView = new ModelAndView();
        Test test = new Test();
        test = testRepository.findAllById(id);
        System.out.println(questionId + "  i  " + " ->> " + id);
        testRepository.insertQuestion(questionId, id);

        modelAndView.setViewName("testAddQuestion");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/test/{id}/addQuestion{id2}", method = RequestMethod.GET)
    public ModelAndView aftereditTest() {
        ModelAndView modelAndView = new ModelAndView();

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
        modelAndView.addObject("questions", questionRepository.findAllByTests(id));
        modelAndView.setViewName("testGet");
        return modelAndView;
    }

//    @RequestMapping(value = "/admin/test/get/{id}", method = RequestMethod.DELETE)
//    public ModelAndView deleteQuestionFromTest(@PathVariable("id") int testId, @ModelAttribute Question question) {
//
//            ModelAndView modelAndView = new ModelAndView();
//            System.out.println("test id: " + testId + "  question id: " + question.getId());
//            int questionId = question.getId();
//            deleteQuestionFromTest(questionId, testId);
//
//            modelAndView.setViewName("testGet");
//
//            return modelAndView;
//    }


}
