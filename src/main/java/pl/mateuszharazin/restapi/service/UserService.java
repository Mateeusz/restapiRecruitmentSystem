package pl.mateuszharazin.restapi.service;

import pl.mateuszharazin.restapi.model.User;

public interface UserService {

    public void saveUser(User user);
    public boolean isUserAlreadyPresent(User user);

}
