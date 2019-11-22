package pl.mateuszharazin.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.mateuszharazin.restapi.model.UserAnswer;

import javax.transaction.Transactional;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, Integer> {


    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM user_answer WHERE question_id = :questionId", nativeQuery = true)
    public UserAnswer findByQuestion(@Param("questionId") int questionId);

//    @Modifying
//    @Transactional
//    @Query(value = "", nativeQuery = true)
//    public void save
}
