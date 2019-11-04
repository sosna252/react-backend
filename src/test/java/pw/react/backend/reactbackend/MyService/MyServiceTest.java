package pw.react.backend.reactbackend.MyService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pw.react.backend.reactbackend.Exceptions.NotFoundException;
import pw.react.backend.reactbackend.MyEntityClass.userEntity;
import pw.react.backend.reactbackend.MyRepository.userRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;


@RunWith(MockitoJUnitRunner.Silent.class)
public class MyServiceTest {
    private MyServicee service;

    @Mock
    private userRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = mock(userRepository.class);
        service = new MyServicee(repository);
    }

    @Test
    public void givenNotExistingUser_whenCheckUser_thenNotFoundException() {
        userEntity user = new userEntity();
        user.setLogin("login");

        try {
            service.checkUser(user.getLogin(),0);
            //fail("Should throw NotFoundException");
        } catch (NotFoundException ex) {
            assertThat(ex.getMessage(), is(equalTo("User with login [login] not found.")));
        }
    }


    @Test
    public void givenExistingUser_whenCheckUser_thenReturningUser() {
        userEntity user = new userEntity("login", "firstname", "lastname", "date", "active");

        given(repository.findByLogin("login")).willReturn(user);
        given(service.checkUser(user.getLogin(),0)).willReturn(user);
    }

}
