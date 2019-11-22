package pl.mateuszharazin.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.mateuszharazin.restapi.model.Test;

import javax.transaction.Transactional;
import java.util.List;

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

      @Modifying
      @Query(value = "SELECT t.test_id FROM application\n" +
              "INNER JOIN test_type tt on application.test_type_id = tt.test_type_id\n" +
              "INNER JOIN test t on application.test_type_id = t.test_type_id\n" +
              "WHERE application.test_type_id = (SELECT application.test_type_id\n" +
              "                                  FROM application\n" +
              "                                  WHERE application_id = :appId);", nativeQuery = true)
      @Transactional
      public List<Integer> listTestIdFromApplication(@Param("appId") int appId);

      @Modifying
      @Query(value = "SELECT test_id FROM test_result WHERE application_id = :id;", nativeQuery = true)
      public Integer testIdFromTestResult(@Param("id") int applicationId);

}
