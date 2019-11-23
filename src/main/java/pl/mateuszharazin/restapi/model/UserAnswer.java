package pl.mateuszharazin.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_answer")
@Entity
public class UserAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_answer_id")
    private int id;

    @NotNull
    @Column(name = "answer_option")
    private String answerOption;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id")
    private Question question;

    public UserAnswer(@NotNull String answerOption, Question question) {
        this.answerOption = answerOption;
        this.question = question;
    }

    //    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "result_answer", joinColumns = @JoinColumn(name = "user_answer_id"), inverseJoinColumns = @JoinColumn(name = "test_result_id"))
//    private Set<Question> questions;


}
