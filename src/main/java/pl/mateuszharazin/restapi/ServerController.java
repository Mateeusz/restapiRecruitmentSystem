package pl.mateuszharazin.restapi;


import org.springframework.web.bind.annotation.*;

import java.util.List;



@CrossOrigin(origins="*", allowedHeaders = "*")
@RestController
public class ServerController {
    private final UsersRepo repo;

    ServerController(UsersRepo repository) {
        this.repo = repository;
    }

    @RequestMapping(value = "/api/Users", method = RequestMethod.GET)
    public List<Users> getUsers()
    {
        return repo.findAll();
    }

    @RequestMapping(value = "/api/Users/{id}", method = RequestMethod.GET)
    public Users getUser(@PathVariable int id)
    {
        return repo.findById(id).orElse(new Users(0,"",0));
    }

    @RequestMapping(value = "/api/Users", method = RequestMethod.POST)
    public Users postUser(@RequestBody Users newUser)
    {
        return repo.save(newUser);
    }

    @RequestMapping(value = "/api/Users/{id}", method = RequestMethod.PUT)
    public Users putUser(@PathVariable int id, @RequestBody Users newUser)
    {
        return repo.findById(id)
                .map(user -> {
                    if(newUser.getName()!=null&&!newUser.getName().isEmpty())
                    {
                        user.setName(newUser.getName());
                    }
                    if(newUser.getAge()!=null&&newUser.getAge()>0)
                    {
                        user.setAge(newUser.getAge());
                    }
                    return repo.save(user);
                })
                .orElseGet(() -> {
                    newUser.setUser_id(id);
                    return repo.save(newUser);
                });
    }

    @RequestMapping(value = "/api/Users/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable int id)
    {
        try
        {
            repo.deleteById(id);
        }
        catch(Exception e)
        {
            throw e;
        }
    }
}
