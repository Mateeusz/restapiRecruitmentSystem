package pl.mateuszharazin.restapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.mateuszharazin.restapi.model.*;
import pl.mateuszharazin.restapi.repository.*;
import pl.mateuszharazin.restapi.service.ApplicationTestService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class TestController {

    @Autowired
    TestRepository testRepository;
    @Autowired
    TestTypeRepository testTypeRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    UserAnswerRepository userAnswerRepository;
    @Autowired
    ApplicationTestRepository applicationTestRepository;

    @Autowired
    ApplicationTestService applicationTestService;

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
    
    @RequestMapping(value = "/admin/test/{id}/addQuestion/{id2}", method = RequestMethod.POST)
    public ModelAndView addQuestion(@PathVariable("id") int id, @PathVariable("id2") int questionId) {

        ModelAndView modelAndView = new ModelAndView();
        System.out.println(questionId + "  i  " + " ->> " + id);
        testRepository.insertQuestion(questionId, id);

        modelAndView.setViewName("testAddQuestion");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/test/{id}/deleteQuestion/{id2}", method = RequestMethod.POST)
    public ModelAndView deleteQuestion(@PathVariable("id") int id, @PathVariable("id2") int questionId) {

        ModelAndView modelAndView = new ModelAndView();
        System.out.println(questionId + "  i  " + " ->> " + id);
        testRepository.deleteQuestionFromTest(questionId, id);

        modelAndView.setViewName("testAddQuestion");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/test/{id}/deleteQuestion/{id2}", method = RequestMethod.GET)
    public ModelAndView afterDeleteQuestionTest() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("testAddQuestion");
        return modelAndView;
    }


    @RequestMapping(value = "/admin/test/{id}/addQuestion{id2}", method = RequestMethod.GET)
    public ModelAndView aftereditTest() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("testAddQuestion");
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

    @RequestMapping(value = "/home/test/resolve/before/{id}", method = RequestMethod.GET)
    public ModelAndView beforeTest(@PathVariable("id") int applicationId, Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("appli", applicationRepository.findAllById(applicationId));
        modelAndView.setViewName("beforeSeeTest");
        return modelAndView;
    }



    @RequestMapping(value = "/home/test/resolve/{idApp}", method = RequestMethod.GET)
    public ModelAndView getTestToSolve(@PathVariable("idApp") int applicationId, Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView();
        User user = userRepository.findByEmail(authentication.getName());
        Application application = applicationRepository.findAllById(applicationId);
        List<UserAnswer> results = new ArrayList<>();
        modelAndView.addObject("user", user);
        modelAndView.addObject("app", application);
        modelAndView.addObject("results", results);

        int testId = applicationTestService.insertNewResultRow(applicationId);

        if(testId==0 || (user.getId()!=application.getUser().getId())) {
            modelAndView.addObject("message", "Brak dostępu do testu we wskazanej aplikacji");
        }
        else if(testId > 0 && user.getId()==application.getUser().getId()){
            modelAndView.addObject("questions", questionRepository.findAllByTests(testId));
            modelAndView.addObject("message", "Test nr ");
        }

        modelAndView.setViewName("testUserGet");
        return modelAndView;
    }

    @RequestMapping(value = "/home/test/resolve/{idApp}", method = RequestMethod.POST)
    public ModelAndView computeAnswers(HttpServletRequest request, @PathVariable("idApp") int applicationId) {
        ModelAndView modelAndView = new ModelAndView();

        int score = 0;
        String []questionsIds = request.getParameterValues("questionId");
        for (String q:questionsIds) {
            Question question = questionRepository.findAllById(Integer.parseInt(q));

            System.out.println("----------> " + Integer.parseInt(q));
            System.out.println(request.getParameter(q));
            System.out.println(question.getAnswer());
            if(request.getParameter(q).equals(question.getAnswer())) {
                score++;
            }

        }
        System.out.println(applicationId + "  i  " + score);
        applicationTestRepository.updateAfterTest(applicationId, score);

        modelAndView.setViewName("testResult");
        return modelAndView;
    }

    @RequestMapping(value = "/home/get/{id}", method = RequestMethod.GET)
    public ModelAndView fun(@PathVariable("id") int id, Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView();
        Application application = applicationRepository.findAllById(id);
//        ApplicationTest applicationTest = applicationTestRepository.findByAppId(id);
        String buttonVar = "true";
        String isTestResult = "false";
        System.out.println(application.getId());
        System.out.println(application.getOffer().getTitle());
//        System.out.println(application.getOffer().toString());
        modelAndView.addObject("appId", id);
        modelAndView.addObject("offerTitle", application.getOffer().getTitle());
        modelAndView.addObject("appStatus", application.getApplicationStatus().getStatusName());
        modelAndView.addObject("cv", application.getCvAttachment());

        modelAndView.addObject("isTestResult", isTestResult);

        if(application.getTestType() != null || application.getApplicationTest() != null) {
            buttonVar = "false";
        }
//        System.out.println(applicationTestRepository.findByAppId(id));
//        if(applicationTestRepository.findTestScore(id) >= 0) {
//            modelAndView.addObject("result", applicationTestRepository.findTestScore(id));
//            isTestResult = "true";
//            modelAndView.addObject("questionsQuan", questionRepository.findAllByTests(applicationTestRepository.findTestId(id)).size());
//        }

        modelAndView.addObject("testButton", buttonVar);
        modelAndView.setViewName("appli");


        return modelAndView;
    }
}
