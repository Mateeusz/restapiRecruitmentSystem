package pl.mateuszharazin.restapi.service;

import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.mateuszharazin.restapi.model.Role;
import pl.mateuszharazin.restapi.model.User;
import pl.mateuszharazin.restapi.repository.RoleRepository;
import pl.mateuszharazin.restapi.repository.UserRepository;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserServiceImp implements UserService {


    @Autowired
    BCryptPasswordEncoder encoder;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;


    @Override
    public void saveUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setStatus("VERIFIED");
        user.setPhoneNumber("123567098");
        Role userRole = roleRepository.findByRoleName("SITE_USER");

        System.out.println("TUTAJ: ->> " + user.toString());
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
//        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
        System.out.println("TUTAJ: 2 ->> " + user.toString());
        System.out.println("lalala--->>>>>   + " + userRole.getId() );
        System.out.println("DONE");
    }

    @Override
    public boolean isUserAlreadyPresent(User user) {
        boolean isUserAlreadyExists = false;
        User existingUser = userRepository.findByEmail(user.getEmail());
        // If user is found in database, then then user already exists.
        if(existingUser != null){
            isUserAlreadyExists = true;
        }
        return isUserAlreadyExists;
    }
}
