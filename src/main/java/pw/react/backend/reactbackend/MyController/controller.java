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
            return new ResponseEntity<>(repo.save(newUser), HttpStatus.OK);
        throw new WrongDataException(String.format("User with login [%s] already exists.", newUser.getLogin()), 25);
    }

    @GetMapping("/login/{login}")
    public ResponseEntity<userEntity> findByLogin(@PathVariable String login) {
        return new ResponseEntity<>(service.checkUser(login, 32), HttpStatus.OK);
    }

    @GetMapping("/{login}")
    public String findByLog(@PathVariable String login) {
        service.checkUser(login, 37);
        return "The user have been already created";
    }

    @GetMapping("/user/{login}")                                                                     //The same as above "/login/{login}", but i can't see the difference between tasks
    public ResponseEntity<userEntity> retrive(@PathVariable String login) {
        return new ResponseEntity<>(service.checkUser(login, 43), HttpStatus.OK);
    }

    @PutMapping("/{login}")
    public ResponseEntity<userEntity> update(@PathVariable String login, @RequestBody userEntity newUser) {
        userEntity user = service.checkUser(login, 37);
        if(repo.findByLogin(newUser.getLogin())!=null)
            throw new WrongDataException(String.format("User with login [%s] already exists.", newUser.getLogin()), 49);
        newUser.setId(user.getId());
        return new ResponseEntity<>(repo.save(newUser), HttpStatus.OK);
    }

    @DeleteMapping("/{login}")
    public ResponseEntity<String> delete(@PathVariable String login) {
        repo.delete(service.checkUser(login, 57));
        return new ResponseEntity<>("User Deleted", HttpStatus.OK);
    }

}
