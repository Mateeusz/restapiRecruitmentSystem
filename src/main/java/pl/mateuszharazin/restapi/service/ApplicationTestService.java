package pl.mateuszharazin.restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import pl.mateuszharazin.restapi.repository.ApplicationTestRepository;

public interface ApplicationTestService {

    public int insertNewResultRow(int applicationId);
    public void updateResults(int applicationId, int testScore);
}
