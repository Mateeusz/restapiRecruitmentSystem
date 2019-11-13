package pl.mateuszharazin.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.mateuszharazin.restapi.model.Application;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {

    @Query(value = "SELECT *\n" +
            "FROM application\n" +
            "WHERE user_id = (SELECT user_id\n" +
            "                 FROM user_table\n" +
            "                 WHERE email = :email) AND application_status_id != 1;" , nativeQuery = true)
    public List<Application> usersApplications(@Param("email") String email);


    @Query(value = "SELECT *\n" +
            "FROM application\n" +
            "WHERE user_id = (SELECT user_id\n" +
            "                 FROM user_table\n" +
            "                 WHERE email = :email) AND application_status_id = 1;" , nativeQuery = true)
    public List<Application> usersEndsApplications(@Param("email") String email);

    public Application findAllById(int id);

    @Query(value = "SELECT *\n" +
            "FROM application\n" +
            "WHERE application_status_id != 1;" , nativeQuery = true)
    public List<Application> activeApplications();

    @Query(value = "SELECT *\n" +
            "FROM application\n" +
            "WHERE application_status_id = 1;" , nativeQuery = true)
    public List<Application> endedApplications();
}
