package pw.react.backend.reactbackend.MyController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pw.react.backend.reactbackend.MyEntityClass.userEntity;
import pw.react.backend.reactbackend.MyRepository.userRepository;
import pw.react.backend.reactbackend.MyService.MyServicee;

@RestController
public class controller {
    @Autowired
    userRepository repo;
    @Autowired
    MyServicee service;

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
}
