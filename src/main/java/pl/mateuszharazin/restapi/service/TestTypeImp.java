package pl.mateuszharazin.restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mateuszharazin.restapi.model.TestType;
import pl.mateuszharazin.restapi.repository.TestTypeRepository;

@Service
public class TestTypeImp implements TestTypeService{

    @Autowired
    TestTypeRepository testTypeRepository;

    public boolean isTestTypeAlreadyExist(TestType testType) {

        boolean isTestTypeExist = false;

        TestType existingTestType = testTypeRepository.findByTestTypeName(testType.getTestTypeName());
        if(existingTestType != null) {
            isTestTypeExist = true;
        }

        return isTestTypeExist;
    }

}
