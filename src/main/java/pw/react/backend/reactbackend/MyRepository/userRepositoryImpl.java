package pw.react.backend.reactbackend.MyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pw.react.backend.reactbackend.MyEntityClass.userEntity;
import pw.react.backend.reactbackend.MyService.MyServicee;

@Repository
public abstract class userRepositoryImpl implements userRepository {
    @Autowired
    MyServicee service;
    @Override
    public userEntity findByLogin(String login) {
        return service.checkUser(login);
    }
}
