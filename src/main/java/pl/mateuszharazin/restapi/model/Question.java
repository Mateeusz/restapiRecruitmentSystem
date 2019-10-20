package pl.mateuszharazin.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    int id;

    @Column(name = "question_body")
    @NotNull
    String questionBody;

    @Column(name = "answer")
    @NotNull
    String answer;

    @Column(name = "option1")
    @NotNull
    String option1;

    @Column(name = "option2")
    @NotNull
    String option2;

    @Column(name = "option3")
    @NotNull
    String option3;

    @Column(name = "option4")
    @NotNull
    String option4;

}
