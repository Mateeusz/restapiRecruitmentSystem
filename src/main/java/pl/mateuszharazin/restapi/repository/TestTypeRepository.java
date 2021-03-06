package pl.mateuszharazin.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mateuszharazin.restapi.model.TestType;

public interface TestTypeRepository extends JpaRepository<TestType, Integer> {

    public TestType findAllById(int id);
    public TestType findByTestTypeName(String name);
}
