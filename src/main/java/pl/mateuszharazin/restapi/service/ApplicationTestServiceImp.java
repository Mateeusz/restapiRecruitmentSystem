package pl.mateuszharazin.restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.mateuszharazin.restapi.model.*;
import pl.mateuszharazin.restapi.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ApplicationTestServiceImp implements ApplicationTestService{

    @Autowired
    ApplicationTestRepository applicationTestRepository;
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    TestRepository testRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    UserAnswerRepository userAnswerRepository;

//    @RequestMapping(value = "/home/test/resolve/{idApp}", method = RequestMethod.GET)
//    public ModelAndView getTestToSolve(@PathVariable("idApp") int applicationId, Authentication authentication) {
//        ModelAndView modelAndView = new ModelAndView();
//        User user = userRepository.findByEmail(authentication.getName());
//        Application application = applicationRepository.findAllById(applicationId);
//        modelAndView.addObject("user", user);
//
//        System.out.println("authentitacion: " + user.getId() );
//        System.out.println("application: " + application.getUser().getId() );
////|| (testRepository.testIdFromTestResult(applicationId).equals(null))
//        if((aviliableTests.size()==0) || (user.getId()!=application.getUser().getId())) {
//            modelAndView.addObject("message", "Brak dostępu do testu we wskazanej aplikacji");
//        }
//        else if(aviliableTests.size()>0 && user.getId()==application.getUser().getId()){
//            Random random = new Random();
//            modelAndView.addObject("questions", questionRepository.);
//            modelAndView.addObject("message", "Test nr ");
//
//        }
    public int insertNewResultRow(int applicationId) {

        int testId = 0;
        Random random = new Random();
        List<Integer> availableTests = testRepository.listTestIdFromApplication(applicationId);
        if(availableTests.size() == 1) {
            testId = availableTests.get(0);
        }
        else if(availableTests.size() > 0) {
            testId = availableTests.get(random.nextInt((availableTests.size()-1)+1)+0);
        }

        applicationTestRepository.setNewRowInSolution(testId, applicationId);
        return testId;
    }

    public void updateResults(int applicationId, int testScore) {


//        List<Question> questions = questionRepository.findAllByTests(applicationTestRepository.findTestId(applicationId));
//
//        List<UserAnswer> userAnswers = new ArrayList<>();
//        for(Question question : questions) {
//            userAnswers.add(userAnswerRepository.findByQuestion(question.getId()));
//        }


    }

}
