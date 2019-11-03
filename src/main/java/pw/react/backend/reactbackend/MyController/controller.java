package pw.react.backend.reactbackend.MyController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pw.react.backend.reactbackend.Exceptions.WrongDataException;
import pw.react.backend.reactbackend.MyEntityClass.userEntity;
import pw.react.backend.reactbackend.MyRepository.userRepository;
import pw.react.backend.reactbackend.MyService.MyServicee;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path = "/users")
public class controller {
    @Autowired
    userRepository repo;
    @Autowired
    MyServicee service;
    HttpServletResponse response;

    @PostMapping("")
    public ResponseEntity<userEntity> create(@RequestBody userEntity newUser){
        if(repo.findByLogin(newUser.getLogin())==null)
            return new ResponseEntity<userEntity>(repo.save(newUser), HttpStatus.OK );
        throw new WrongDataException(String.format("User with login [%s] already exists.", newUser.getLogin()));
    }

    @GetMapping("/login/{login}")
    public userEntity findByLogin(@PathVariable String login) {
        return service.checkUser(login, 32);
    }

    @GetMapping("/{login}")
    public String findByLog(@PathVariable String login) {
        service.checkUser(login, 37);
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
