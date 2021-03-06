package pl.mateuszharazin.restapi.controllers;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import pl.mateuszharazin.restapi.model.Application;
import pl.mateuszharazin.restapi.model.ApplicationStatus;
import pl.mateuszharazin.restapi.repository.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ApplicationController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    OfferRepository offerRepository;
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    ApplicationStatusRepository applicationStatusRepository;
    @Autowired
    TestTypeRepository testTypeRepository;

    @RequestMapping(value = "/apply/{id}", method = RequestMethod.GET)
    public ModelAndView applyForJob(@PathVariable(name = "id") int offerId, Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("offer", offerRepository.findById(offerId));
        modelAndView.addObject("user", userRepository.findByEmail(authentication.getName()));
        modelAndView.setViewName("applicationForm");
        return modelAndView;
    }

    @RequestMapping(value = "apply/{id}", method = RequestMethod.POST)
    public ModelAndView apply(@PathVariable(name = "id") int offerId, Authentication authentication,
                               @RequestPart(name = "fileupload") MultipartFile file, @ModelAttribute Application application,
                                BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", userRepository.findByEmail(authentication.getName()));
        modelAndView.addObject("offer", offerRepository.findById(offerId));

        application.setOffer(offerRepository.findAllById(offerId));
        application.setUser(userRepository.findByEmail(authentication.getName()));


        if(!bindingResult.hasErrors()) {
            File uploadDirectory = new File("uploads");
            uploadDirectory.mkdirs();    // 3

            try {
                File oFile = new File("uploads/" + file.getOriginalFilename());
                OutputStream os = new FileOutputStream(oFile);
                InputStream inputStream = file.getInputStream();

                application.setCvAttachment(oFile.getName());

                IOUtils.copy(inputStream, os); // 4
                os.close();
                inputStream.close();

                application.setApplicationStatus(applicationStatusRepository.findById(1));
                applicationRepository.save(application);
                modelAndView.addObject("successMessage", "Zaaplikowano pomyślnie!");
            } catch (IOException e) {
                e.printStackTrace();
                modelAndView.addObject("successMessage" , "Wystąpił błąd podczas przesyłania pliku! ");
            }

        } else {
                modelAndView.addObject("successMessage", "Nie zaaplikowano!");

        }
        modelAndView.setViewName("applicationForm");
        return modelAndView;
    }




    @RequestMapping(value = "/home/myApplications", method = RequestMethod.GET)
    public ModelAndView myApplications(Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView();
        List<Application> myApplications = applicationRepository.usersApplications(authentication.getName());

        modelAndView.addObject("myApplications", applicationRepository.usersApplications(authentication.getName()));
        modelAndView.setViewName("myLiveAppUser");

        return modelAndView;
    }

    @RequestMapping(value = "/home/application/{id}", method = RequestMethod.GET)
    ModelAndView getApplication(@PathVariable("id") int applicationId) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("app", applicationRepository.findById(applicationId));
//        Application application1 = applicationRepository.findAllById(id);
//        modelAndView.addObject(applicationRepository.findAllById(id));
//        modelAndView.addObject("applicat", applicationRepository.findAllById(id));
        modelAndView.setViewName("getApp");

        return modelAndView;
    }

    @RequestMapping(value = "/admin/application/{id}", method = RequestMethod.GET)
    public ModelAndView getApplicationAdmin(@PathVariable("id") int applicationId) {

        ModelAndView modelAndView = new ModelAndView();
//
        Application application = applicationRepository.findAllById(applicationId);
//        ApplicationTest applicationTest = applicationTestRepository.findByAppId(id);
        String buttonVar = "true";
        String isTestResult = "false";
        System.out.println(application.getId());
        System.out.println(application.getOffer().getTitle());
//        System.out.println(application.getOffer().toString());
        modelAndView.addObject("appId", applicationId);
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
        modelAndView.setViewName("getApp");

        return modelAndView;
    }

//    @RequestMapping(value = "/home/myApp/{id}", method = RequestMethod.GET)
//    ModelAndView selectMyApp(@PathVariable("id") int applicationId, Authentication authentication) {
//        ModelAndView modelAndView = new ModelAndView();
//        Application application = applicationRepository.findAllById(applicationId);
//        System.out.println(application.getUser().getFirstName());
//        System.out.println(application.getOffer().getTitle());
//
//        modelAndView.addObject("offerName", application.getOffer().getTitle());
//        modelAndView.addObject("application", application);
//        modelAndView.setViewName("appli");
//        return modelAndView;
//    }

    @RequestMapping(value = "/home/myEndApplications", method = RequestMethod.GET)
    public ModelAndView myEndApplications(Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("myEndApplications", applicationRepository.usersEndsApplications(authentication.getName()));
        modelAndView.setViewName("myEndAppUser");

        return modelAndView;
    }

    @RequestMapping(value = "/admin/applications", method = RequestMethod.GET)
    public ModelAndView applications() {
        ModelAndView modelAndView = new ModelAndView();
        List<Application> applications = applicationRepository.findAll();

        modelAndView.addObject("applications", applicationRepository.activeApplications());
        modelAndView.setViewName("adminLiveApp");

        return modelAndView;
    }

    @RequestMapping(value = "/admin/applications/ended", method = RequestMethod.GET)
    public ModelAndView endedApplications() {
        ModelAndView modelAndView = new ModelAndView();
        List<Application> applications = applicationRepository.findAll();

        modelAndView.addObject("applications", applicationRepository.endedApplications());
        modelAndView.setViewName("adminEndApp");

        return modelAndView;
    }


    @GetMapping("/upload")
    public String uploadGet() {
        return "applicationForm";
    }

    @PostMapping("/upload")
    @ResponseBody            // 1
    public String handleFile(@RequestPart(name = "fileupload") MultipartFile file) { // 2
        File uploadDirectory = new File("uploads");
        uploadDirectory.mkdirs();    // 3

        try {
            File oFile = new File("uploads/" + file.getOriginalFilename());
            OutputStream os = new FileOutputStream(oFile);
            InputStream inputStream = file.getInputStream();

            IOUtils.copy(inputStream, os); // 4
            os.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return "Wystąpił błąd podczas przesyłania pliku: " + e.getMessage();
        }

        return "ok!";
    }

    @RequestMapping(value = "/admin/applications/change/{id}", method = RequestMethod.GET)
    public ModelAndView changeStatus(@PathVariable("id") int applicationId) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("appli", applicationRepository.findAllById(applicationId));
        modelAndView.addObject("statuses", applicationStatusRepository.findAll());
        modelAndView.setViewName("changeAppStatus");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/applications/change/{id}", method = RequestMethod.POST)
    public ModelAndView createTest(@ModelAttribute Application appli,
                                   BindingResult bindingResult, ModelMap modelMap,
                                   @PathVariable("id") int applicationId) {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("appli", applicationRepository.findAllById(applicationId));
        modelAndView.addObject("statuses", applicationStatusRepository.findAll());

        if(bindingResult.hasErrors()) {
            modelAndView.addObject("successMessage", "Please correct the errors in form!");
            modelMap.addAttribute("bindingResult", bindingResult);
        }
        else {
            applicationRepository.changeAppStatus(appli.getApplicationStatus().getId(), applicationId);
            modelAndView.addObject("successMessage", "Status has been updated successfully!");
        }


        modelAndView.setViewName("testList");


        return modelAndView;
    }


}
