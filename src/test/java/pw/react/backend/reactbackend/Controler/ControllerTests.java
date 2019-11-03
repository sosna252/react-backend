package pw.react.backend.reactbackend.Controler;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pw.react.backend.reactbackend.Exceptions.NotFoundException;
import pw.react.backend.reactbackend.MyController.controller;
import pw.react.backend.reactbackend.MyEntityClass.userEntity;
import pw.react.backend.reactbackend.MyRepository.userRepository;

import java.util.Optional;

import static org.assertj.core.api.Fail.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class ControllerTests {
    private controller cntr;

    @Mock
    private userRepository repository;

    @Before
    public void setUp() throws Exception {
        cntr = spy(new controller());
    }

    @Test
    public void givenExistingUser_whenCreateUser_thenWrongDataException() {
        userEntity user = new userEntity("aaa","bbb","ccc","ddd","eee");
        repository.save(user);

        try {
            cntr.create(user);
            fail("Should throw NotFoundException");
        } catch (NotFoundException ex) {
            assertThat(ex.getMessage(), is(equalTo("User with login [aaa] already exists.")));
        }
        verify(repository, times(0)).save(any(userEntity.class));
    }

    @Test
    public void givenNotExistingUser_whenCreateUser_thenExecuteSaveMethod() {
        userEntity user = new userEntity("aaa","bbb","ccc","ddd","eee");

        cntr.create(user);

        verify(repository, times(1)).save(eq(user));
    }


    @Test
    public void givenNotExistingUser_findByLogin_thenNotFoundException() {
        userEntity user = new userEntity("aaa","bbb","ccc","ddd","eee");
        repository.save(user);

        try {
            cntr.findByLogin(user.getLogin());
            fail("Should throw NotFoundException");
        } catch (NotFoundException ex) {
            assertThat(ex.getMessage(), is(equalTo("User with login [login] not found.")));
        }
        verify(repository, times(0)).findByLogin(user.getLogin());
    }

    @Test
    public void givenExistingUser_findByLogin_thenReturningUser() {
        userEntity user = new userEntity("aaa","bbb","ccc","ddd","eee");

        when(repository.findById(anyLong())).thenReturn(Optional.of(user));

        cntr.findByLogin(user.getLogin());

        verify(repository, times(1)).findByLogin(eq(user.getLogin()));
    }

    @Test
    public void givenNotExistingUser_findByLog_thenNotFoundException() {
        userEntity user = new userEntity("aaa","bbb","ccc","ddd","eee");
        repository.save(user);

        try {
            cntr.findByLog(user.getLogin());
            fail("Should throw NotFoundException");
        } catch (NotFoundException ex) {
            assertThat(ex.getMessage(), is(equalTo("User with login [login] not found.")));
        }
        verify(repository, times(0)).findByLogin(user.getLogin());
    }

    @Test
    public void givenExistingUser_findByLog_thenReturningString() {
        userEntity user = new userEntity("aaa","bbb","ccc","ddd","eee");

        when(repository.findById(anyLong())).thenReturn(Optional.of(user));

        cntr.findByLog(user.getLogin());

        verify(repository, times(1)).findByLogin(eq(user.getLogin()));
    }


    @Test
    public void givenNotExistingUser_whenUpdateUser_thenNotFoundException() {
        userEntity user = new userEntity();
        user.setLogin("login");
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        try {
            cntr.update(user.getLogin(),user);
            fail("Should throw NotFoundException");
        } catch (NotFoundException ex) {
            assertThat(ex.getMessage(), is(equalTo("User with login [login] not found.")));
        }
        verify(repository, times(0)).save(any(userEntity.class));
    }

    @Test
    public void givenExistingUser_AndExistingLogin_whenUpdateUser_thenWrongDataException() {
        userEntity user = new userEntity();
        user.setLogin("login");
        repository.save(user);

        try {
            cntr.update(user.getLogin(),user);
            fail("Should throw WrongDataException");
        } catch (NotFoundException ex) {
            assertThat(ex.getMessage(), is(equalTo("User with login [login] already exists.")));
        }
        verify(repository, times(0)).save(any(userEntity.class));
    }

    @Test
    public void givenExistingUser_AndNotExistingLogin__whenCheckUser_thenReturningUser() {
        userEntity user = new userEntity();
        user.setLogin("login");
        repository.save(user);

        cntr.update("newLogin",user);

        verify(repository, times(1)).findByLogin(eq("newLogin"));
        verify(repository, times(0)).findByLogin(eq("login"));
    }

    @Test
    public void givenNotExistingUser_DeleteUser_thenNotFoundException() {
        userEntity user = new userEntity();
        user.setLogin("login");
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        try {
            cntr.delete(user.getLogin());
            fail("Should throw NotFoundException");
        } catch (NotFoundException ex) {
            assertThat(ex.getMessage(), is(equalTo("User with login [login] not found.")));
        }
        verify(repository, times(0)).findByLogin(user.getLogin());
    }

    @Test
    public void givenExistingUser_DeleteUser_thenReturning() {
        userEntity user = new userEntity("aaa","bbb","ccc","ddd","eee");
        repository.save(user);

        cntr.delete(user.getLogin());

        verify(repository, times(0)).findByLogin(eq(user.getLogin()));
    }

}