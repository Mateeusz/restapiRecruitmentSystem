package pl.mateuszharazin.restapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.mateuszharazin.restapi.model.Offer;
import pl.mateuszharazin.restapi.model.Question;
import pl.mateuszharazin.restapi.repository.QuestionRepository;

import javax.validation.Valid;

@Controller
public class QuestionController {

    @Autowired
    QuestionRepository questionRepository;

    @RequestMapping(value = "/admin/test/question/edit/{id}", method = RequestMethod.GET)
    public ModelAndView getQuestion(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        Question question = questionRepository.findAllById(id);
        modelAndView.addObject("question", question);
        modelAndView.setViewName("questionEdit");
        return modelAndView;
    }



    @RequestMapping(value = "/admin/test/question/edit/{id}", method = RequestMethod.POST)
    public ModelAndView putQuestion(@PathVariable(value = "id") int id, @Valid @ModelAttribute Question question) {

//        TODO: add if ok if error
        ModelAndView modelAndView = new ModelAndView();
        Question question1 = questionRepository.findAllById(id);
        question1.setQuestionBody(question.getQuestionBody());
        question1.setOption1(question.getOption1());
        question1.setOption2(question.getOption2());
        question1.setOption3(question.getOption3());
        question1.setOption4(question.getOption4());
        question1.setAnswer(question.getAnswer());

        modelAndView.addObject("question", question);
        questionRepository.save(question1);
        modelAndView.addObject("successMessage", "User is registered successfully!");

        modelAndView.setViewName("questionEdit");
        return modelAndView;
    }

}
