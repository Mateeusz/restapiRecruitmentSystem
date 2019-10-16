//package pl.mateuszharazin.restapi.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import pl.mateuszharazin.restapi.model.Application;
//import pl.mateuszharazin.restapi.model.ApplicationStatus;
//import pl.mateuszharazin.restapi.model.Offer;
//import pl.mateuszharazin.restapi.model.User;
////import pl.mateuszharazin.restapi.repository.ApplicationRepository;
//import pl.mateuszharazin.restapi.repository.ApplicationStatusRepository;
//import pl.mateuszharazin.restapi.repository.OfferRepository;
//import pl.mateuszharazin.restapi.repository.UserRepository;
//
//import javax.validation.constraints.Null;
//import java.util.Optional;
//
//@Service
//public class ApplicationServiceImp implements ApplicationService {
//
//    @Autowired
//    ApplicationRepository applicationRepository;
//    @Autowired
//    ApplicationStatusRepository applicationStatusRepository;
//    @Autowired
//    OfferRepository offerRepository;
//    @Autowired
//    UserRepository userRepository;
//
//
//
//    @Override
//    public void addNewApplication(int jobOfferId, String userEmail, String cv, int applicationStatusId) {
//
//        User user = userRepository.findByEmail(userEmail);
//        Offer offer = offerRepository.findAllById(jobOfferId);
//        Optional<ApplicationStatus> applicationStatus = applicationStatusRepository.findById(applicationStatusId);
//
////        TODO: logika biznesowa tnz. sprawdzanie
//        Application application = new Application(cv, user, offer, applicationStatus);
//        applicationRepository.save(application);
//
//    }
//
//    @Override
//    public void changeApplicationStatus(int applicationId, int newApplicationStatusId) {
//
////        TODO: coś nie do końca działa jakby w tych obiektach nie widział jednego unikalnego.
////        TODO: Sprawdź modele czy dobrze działają, przetestuj w main'ie tworząc ich obiekty
//        Application application = applicationRepository.findById(applicationId);
//
//    }
//
//    @Override
//    public void addTestTypeToApplication(int applicationId) {
//
//    }
//
//    @Override
//    public void closeApplication(int applicationId) {
//
//    }
//}
