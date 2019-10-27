package pw.react.backend.reactbackend.MyService;

import org.springframework.beans.factory.annotation.Autowired;
import pw.react.backend.reactbackend.MyEntityClass.userEntity;
import pw.react.backend.reactbackend.MyRepository.userRepository;

import java.util.ArrayList;

@org.springframework.stereotype.Service
public class MyServicee {
    @Autowired
    private userRepository repo;

    public userEntity checkUser(String login) {
        ArrayList<userEntity> users;
        users = (ArrayList<userEntity>) repo.findAll();
        for (userEntity user : users) {
            if (user.getLogin()!=null && user.getLogin().equals(login))
                return user;
        }
        return new userEntity();
    }
}
