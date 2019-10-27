package pw.react.backend.reactbackend.MyRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pw.react.backend.reactbackend.MyEntityClass.userEntity;

@Repository
public interface userRepository extends CrudRepository<userEntity, Long> {
    userEntity findByLogin(String login);
}


