package pw.react.backend.reactbackend.MyService;

import org.springframework.beans.factory.annotation.Autowired;
import pw.react.backend.reactbackend.Exceptions.NotFoundException;
import pw.react.backend.reactbackend.MyEntityClass.userEntity;
import pw.react.backend.reactbackend.MyRepository.userRepository;

@org.springframework.stereotype.Service
public class MyServicee {
    @Autowired
    private userRepository repo;

    public userEntity checkUser(String login, int line) {
        userEntity user = repo.findByLogin(login);
        if(user!=null)
            return user;

        throw new NotFoundException(String.format("User with login [%s] not found.", login), line);

    }
}
