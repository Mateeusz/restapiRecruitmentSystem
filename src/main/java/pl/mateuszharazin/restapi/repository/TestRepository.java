package pl.mateuszharazin.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.mateuszharazin.restapi.model.Test;

import javax.transaction.Transactional;

public interface TestRepository extends JpaRepository<Test, Integer> {


      @Modifying
      @Query(value = "INSERT INTO question_test (question_id, test_id) VALUES (:question_id, :test_id)", nativeQuery = true)
      @Transactional
      public void insertQuestion(@Param("question_id") int questionId, @Param("test_id") int testId);

      @Modifying
      @Query(value = "DELETE FROM question_test WHERE ((question_id = :question_id) AND (test_id = :test_id))", nativeQuery = true)
      @Transactional
      public void deleteQuestionFromTest(@Param("question_id") int questionId, @Param("test_id") int testId);

      public Test findAllById(int id);
}
