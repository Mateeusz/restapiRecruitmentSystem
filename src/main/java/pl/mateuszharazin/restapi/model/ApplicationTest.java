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
@Entity(name = "test_result")
public class ApplicationTest {

    @Id
    @Column(name = "test_result_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "test_score")
    @NotNull
    private int testScore;

    @Column(name = "solution_date")
    @NotNull
    private java.sql.Date solutionDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private Test test;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private Application application;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "result_answer", joinColumns = @JoinColumn(name = "user_answer_id"), inverseJoinColumns = @JoinColumn(name = "test_result_id"))
    private Set<UserAnswer> userAnswers;

    public ApplicationTest(int testScore, java.sql.Date solutionDate, Test test, Application application) {
        this.testScore = testScore;
        this.solutionDate = solutionDate;
        this.test = test;
        this.test.setApplicationTest(this);
        this.application = application;
        this.application.setApplicationTest(this);
    }


}
