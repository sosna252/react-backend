package pw.react.backend.reactbackend.MyService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pw.react.backend.reactbackend.Exceptions.NotFoundException;
import pw.react.backend.reactbackend.MyEntityClass.userEntity;
import pw.react.backend.reactbackend.MyRepository.userRepository;

import java.util.Optional;

import static org.assertj.core.api.Fail.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class MyServiceTest {
    private MyServicee service;

    @Mock
    private userRepository repository;

    @Before
    public void setUp() throws Exception {
        service = spy(new MyServicee());
    }

    @Test
    public void givenNotExistingUser_whenCheckUser_thenNotFoundException() {
        userEntity user = new userEntity();
        user.setLogin("login");
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        try {
            service.checkUser(user.getLogin(),0);
            fail("Should throw NotFoundException");
        } catch (NotFoundException ex) {
            assertThat(ex.getMessage(), is(equalTo("User with login [login] not found.")));
        }
        verify(repository, times(0)).save(any(userEntity.class));
    }

    @Test
    public void givenExistingUser_whenCheckUser_thenReturningUser() {
        userEntity user = new userEntity();
        user.setLogin("login");
        when(repository.findById(anyLong())).thenReturn(Optional.of(user));

        service.checkUser(user.getLogin(),0);

        verify(repository, times(1)).findByLogin(eq(user.getLogin()));
    }

}
