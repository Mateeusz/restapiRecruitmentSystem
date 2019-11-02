package pl.mateuszharazin.restapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.mateuszharazin.restapi.model.Offer;
import pl.mateuszharazin.restapi.model.Question;
import pl.mateuszharazin.restapi.repository.QuestionRepository;
import pl.mateuszharazin.restapi.service.QuestionService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    QuestionService questionService;

    @RequestMapping(value = "/admin/test/question/add", method = RequestMethod.GET)
    public ModelAndView addQuestion() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("question", new Question());
        modelAndView.setViewName("questionAdd");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/test/question/add", method = RequestMethod.POST)
    public ModelAndView newQuestion(@Valid@ModelAttribute Question question, BindingResult bindingResult, ModelMap modelMap) {

        ModelAndView modelAndView = new ModelAndView();

        if(bindingResult.hasErrors()) {
            modelAndView.addObject("successMessage", "Please correct the errors in form!");
            modelMap.addAttribute("bindingResult", bindingResult);
        }
        else if(questionService.isQuestionAlreadyExist(question)){
            modelAndView.addObject("successMessage", "Question already exists!");
        }
        else {
            questionService.saveQuestion(question);
            modelAndView.addObject("successMessage", "Question has been added successfully!");
        }
        modelAndView.addObject("question", question);

        modelAndView.setViewName("questionAdd");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/test/question/list", method = RequestMethod.GET)
    public ModelAndView listQuestions() {

        ModelAndView modelAndView = new ModelAndView();
        List<Question> questions = questionRepository.findAll();
        modelAndView.addObject("questions", questions);
        modelAndView.setViewName("questionList");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/test/question/edit/{id}", method = RequestMethod.GET)
    public ModelAndView getQuestion(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        Question question = questionRepository.findAllById(id);
        modelAndView.addObject("question", question);
        modelAndView.setViewName("questionEdit");
        return modelAndView;
    }


    @RequestMapping(value = "/admin/test/question/edit/{id}", method = RequestMethod.POST)
    public ModelAndView putQuestion(@PathVariable(value = "id") int id, @Valid @ModelAttribute Question question, BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView();
        Question question1 = questionRepository.findAllById(id);
        question1.setQuestionBody(question.getQuestionBody());
        question1.setOption1(question.getOption1());
        question1.setOption2(question.getOption2());
        question1.setOption3(question.getOption3());
        question1.setOption4(question.getOption4());
        question1.setAnswer(question.getAnswer());
        if(bindingResult.hasErrors()) {
            modelAndView.addObject("successMessage", "Please correct the errors in the form!");
        }
        else {
            questionRepository.save(question1);
            modelAndView.addObject("successMessage", "Question has been modified successfully!");
        }
        modelAndView.addObject("question", question);
        modelAndView.setViewName("questionEdit");

        return modelAndView;
    }

}
