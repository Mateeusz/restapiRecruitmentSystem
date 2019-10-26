package pl.mateuszharazin.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mateuszharazin.restapi.model.Test;

public interface TestRepository extends JpaRepository<Test, Integer> {

}
