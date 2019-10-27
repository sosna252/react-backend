package pw.react.backend.reactbackend.MyController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pw.react.backend.reactbackend.MyEntityClass.userEntity;
import pw.react.backend.reactbackend.MyRepository.userRepository;
import pw.react.backend.reactbackend.MyService.MyServicee;

import javax.servlet.http.HttpServletResponse;

@RestController
public class controller {
    @Autowired
    userRepository repo;
    @Autowired
    MyServicee service;
    HttpServletResponse response;

    @PostMapping("/create")
    public String create(@RequestBody userEntity newUser){
        userEntity user = repo.findByLogin(newUser.getLogin());
        if(user==null)
        {
            repo.save(new userEntity(newUser.getLogin(),newUser.getFirstname(), newUser.getLastname(), newUser.getDateofbirth(), newUser.getActive()));
            return "Created new User successfully";
        }
        return "This login is alredy taken";
    }

    @GetMapping("/findbylogin/{login}")
    public String findByLogin(@PathVariable String login) {
        //userEntity user = service.checkUser(login);
        userEntity user = repo.findByLogin(login);
        if(user == null)
            return "There is no user with this login";
        return "id: " +user.getId() +"\nlogin: " + user.getLogin()+"\nfirstname: " + user.getFirstname()+"\nlastname: " + user.getLastname()+"\ndateofbirth: " + user.getDateofbirth()+"\nactive : " + user.getActive();
    }

    @GetMapping("/ifcreated/{login}")
    public String findByLog(@PathVariable String login) {
        userEntity user = service.checkUser(login);
        if(user.getId() == null)
            return "There is no user with this login";
        return "The user have been already created";
    }

    @GetMapping("/retrive/{login}")
    @SuppressWarnings("unchecked")
    public <T> T retrive(@PathVariable String login, HttpServletResponse res) {
        userEntity user = repo.findByLogin(login);
        if(user != null)
            return (T) (userEntity) user;

        response = res;
        response.setStatus(418);
        return (T) (String)"There is no user with this login";

    }

    @PostMapping("/update/{login}")
    public String update(@PathVariable String login, @RequestBody userEntity newUser, HttpServletResponse res) {
        userEntity user = repo.findByLogin(login);
        if(user != null)
        {
            userEntity user2 = repo.findByLogin(newUser.getLogin());
            if(user2==null)
                user.setLogin(newUser.getLogin());
            else
                return "This login is alredy taken";
            user.setFirstname(newUser.getFirstname());
            user.setLastname(newUser.getLastname());
            user.setDateofbirth(newUser.getDateofbirth());
            user.setActive(newUser.getActive());
            repo.save(user);
            return "User updated";
        }
        response = res;
        response.setStatus(418);
        return "There is no user with this login";
    }


    @GetMapping("/delete/{login}")
    public String delete(@PathVariable String login, HttpServletResponse res) {
        userEntity user = repo.findByLogin(login);
        if(user != null)
        {
            repo.delete(user);
            return "User deleted";
        }
        response = res;
        response.setStatus(418);
        return "There is no user with this login";
    }

}
