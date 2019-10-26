package pl.mateuszharazin.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.mateuszharazin.restapi.model.Question;
import pl.mateuszharazin.restapi.model.Test;

import java.util.List;

public interface TestRepository extends JpaRepository<Test, Integer> {

//    public Test findAllById(int id);



}
