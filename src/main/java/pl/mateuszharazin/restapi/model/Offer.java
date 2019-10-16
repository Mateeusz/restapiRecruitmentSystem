package pl.mateuszharazin.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "job_offer")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_offer_id")
    private int id;

    @NotNull(message = "Job Offer title can not be null!")
    @Column(name = "title")
    private String title;

    @NotNull(message = "Max salary can not be null!")
    @Column(name = "max_salary")
    private double salary;

    @NotNull(message = "Requirements can not be null!")
    @Column(name = "requirements")
    private String requirements;

    @NotNull(message = "Description can not be null!")
    @Column(name = "description")
    private String description;

    @NotNull(message = "Vacant number can not be null!")
    @Column(name = "vacant_number")
    private int vacantNumber;

//    @OneToOne(mappedBy = "offer")
//    private Application application;

}
