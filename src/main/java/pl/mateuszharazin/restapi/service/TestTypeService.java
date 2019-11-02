package pl.mateuszharazin.restapi.service;

import pl.mateuszharazin.restapi.model.TestType;

public interface TestTypeService {

    public boolean isTestTypeAlreadyExist(TestType testType);
}
