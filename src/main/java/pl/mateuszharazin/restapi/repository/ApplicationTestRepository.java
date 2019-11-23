package pl.mateuszharazin.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import pl.mateuszharazin.restapi.model.ApplicationTest;

import javax.transaction.Transactional;

public interface ApplicationTestRepository extends JpaRepository<ApplicationTest, Integer> {

//    At the beginning test solving
    @Modifying
    @Query(value = "INSERT INTO test_result (test_id, application_id) VALUES (:testId, :appId);", nativeQuery = true)
    @Transactional
    public void setNewRowInSolution(@Param("testId") int testId, @Param("appId") int appId);

    @Modifying
    @Query(value = "UPDATE test_result\n" +
            "SET test_score = :score, solution_date = CURRENT_DATE\n" +
            "WHERE application_id = :app ;", nativeQuery = true)
    @Transactional
    public void updateAfterTest(@Param("app") int applicationId, @Param("score") int testScore);

    @Modifying
    @Transactional
    @Query(value = "SELECT test_id FROM test_result WHERE application_id = :appId;", nativeQuery = true)
    public int findTestId(@Param("appId") int appId);

    @Modifying
    @Transactional
    @Query(value = "SELECT test_score FROM test_result WHERE application_id = :appId;", nativeQuery = true)
    public int findTestScore(@Param("appId") int appId);

    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM test_result WHERE application_id = :appId;", nativeQuery = true)
    public ApplicationTest findByAppId(@Param("appId") int appId);
}
