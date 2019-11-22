package pl.mateuszharazin.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "application")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
     int id;

    @Column(name = "cv_attachment")
    @NotNull(message = "CV attachment is required!")
     String cvAttachment;

    @ManyToOne
    @JoinColumn(name = "user_id")
     User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "application_status_id")
     ApplicationStatus applicationStatus;

    @OneToOne(mappedBy = "application")
     ApplicationTest applicationTest;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "test_type_id")
     TestType testType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "job_offer_id")
     Offer offer;


//    public Application(String cv, User user, Offer offer, ApplicationStatus applicationStatus, TestType testType) {
//        this.cvAttachment = cv;
//        this.user = user;
//        this.offer = offer;
////        this.offer.setApplication(this);
//        this.applicationStatus = applicationStatus;
////        this.applicationStatus.setApplication(this);
//        this.testType = testType;
////        this.testType.setApplication(this);
//    }
}
