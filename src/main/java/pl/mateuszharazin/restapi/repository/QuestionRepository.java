package pl.mateuszharazin.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.mateuszharazin.restapi.model.Question;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

//    @Query("SELECT question FROM question INNER JOIN question_test qt on question.question_id = qt.question_id WHERE test_id = :id")
//    public Question findAllById(int id);

    @Query(value = "SELECT * FROM question INNER JOIN question_test qt on question.question_id = qt.question_id WHERE test_id = :id", nativeQuery = true)
    public List<Question> findAllByTests(@Param("id") int id);

    
    @Query(value = "SELECT * FROM question INNER JOIN question_test qt on question.question_id = qt.question_id WHERE test_id = :id", nativeQuery = true)
    public List<Question> findAllByApplication(@Param("id")int id);
//    @Query(value = "INSERT INTO question_test VALUES ")

    public Question findAllById(int id);
    public Question findByQuestionBody(String body);
}
