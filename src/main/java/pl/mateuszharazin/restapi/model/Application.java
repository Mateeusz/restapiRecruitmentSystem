//package pl.mateuszharazin.restapi.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "job_offer")
//public class Application {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "")
//    private int id;
//
//    @Column(name = "cv_attachment")
//    @NotNull(message = "CV attachment is required!")
//    private String cvAttachment;

//    @ManyToOne
//    private User user;
//
//    @OneToOne
//    private Offer offer;
//
//    @OneToOne
//    private TestType testType;
//
//    @OneToOne
//    private ApplicationStatus applicationStatus;
//
//    @OneToOne(mappedBy = "application")
//    private ApplicationTest applicationTest;
//
//    public Application(String cv, User user, Offer offer, ApplicationStatus applicationStatus) {
//        this.cvAttachment = cv;
//        this.user = user;
//        this.offer = offer;
//        this.applicationStatus = applicationStatus;
//    }
//}
