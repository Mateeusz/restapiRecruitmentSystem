package pl.mateuszharazin.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "job_offer")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_offer_id")
     int id;

    @NotNull(message = "Job Offer title can not be null!")
    @Column(name = "title")
     String title;

    @NotNull(message = "Max salary can not be null!")
    @Column(name = "max_salary")
     double salary;

    @NotNull(message = "Requirements can not be null!")
    @Column(name = "requirements")
     String requirements;

    @NotNull(message = "Description can not be null!")
    @Column(name = "description")
     String description;

    @NotNull(message = "Vacant number can not be null!")
    @Column(name = "vacant_number")
     int vacantNumber;

    @NotNull
    @Column(name = "end_date")
     java.sql.Date endDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "offer")
    private Set<Application> applications;

}
