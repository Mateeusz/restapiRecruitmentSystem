package pl.mateuszharazin.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mateuszharazin.restapi.model.TestType;

public interface TestTypeRepository extends JpaRepository<TestType, Integer> {

}
