package pl.mateuszharazin.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "application_status_type")
public class ApplicationStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_status_type_id")
    private int id;

    @NotNull
    @Column(name = "status_name")
    private String statusName;

    @OneToMany(mappedBy = "applicationStatus", cascade = CascadeType.ALL)
    private Set<Application> applications;

    public ApplicationStatus(String statusName) {
        this.statusName = statusName;
    }

}
