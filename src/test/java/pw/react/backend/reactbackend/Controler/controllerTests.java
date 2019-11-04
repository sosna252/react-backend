package pw.react.backend.reactbackend.Controler;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pw.react.backend.reactbackend.Exceptions.NotFoundException;
import pw.react.backend.reactbackend.Exceptions.WrongDataException;
import pw.react.backend.reactbackend.MyController.controller;
import pw.react.backend.reactbackend.MyEntityClass.userEntity;
import pw.react.backend.reactbackend.MyRepository.userRepository;
import pw.react.backend.reactbackend.MyService.MyServicee;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.Fail.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.BDDMockito.given;


@RunWith(MockitoJUnitRunner.Silent.class)
public class controllerTests {
    private controller cntr;
    private MyServicee ser;
    @Mock
    private userRepository repository;


    @Before
    public void setUp() throws Exception {
        ser = new MyServicee(repository);
        cntr = new controller(repository, ser);
    }

    @Test
    public void givenExistingUser_whenCreateUser_thenWrongDataException() {
        userEntity user = new userEntity("aaa","bbb","ccc","ddd","eee");
        given(repository.findByLogin(user.getLogin())).willReturn(user);

        try {
            cntr.create(user);
            fail("Should throw WrongDataException");
        } catch (WrongDataException ex) {
            assertThat(ex.getMessage(), is(equalTo("User with login [aaa] already exists.")));
        }

    }

    @Test
    public void givenNotExistingUser_whenCreateUser_thenExecuteSaveMethod() {
        userEntity user = new userEntity("aaa","bbb","ccc","ddd","eee");
        given(cntr.create(user)).willReturn(ResponseEntity.ok(user));
    }


    @Test
    public void givenNotExistingUser_ByLogin_thenNotFoundException() {
        userEntity user = new userEntity("aaa","bbb","ccc","ddd","eee");
        given(repository.findByLogin(user.getLogin())).willReturn(null);

        try {
            cntr.ByLogin(user.getLogin());
            fail("Should throw NotFoundException");
        } catch (NotFoundException ex) {
            assertThat(ex.getMessage(), is(equalTo("User with login [aaa] not found.")));
        }
    }

    @Test
    public void givenExistingUser_ByLogin_thenReturningUser() {
        userEntity user = new userEntity("aaa","bbb","ccc","ddd","eee");
        given(repository.findByLogin(user.getLogin())).willReturn(user);

        ResponseEntity<userEntity> response = cntr.ByLogin(user.getLogin());
        then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(response.getBody()).isEqualToComparingFieldByField(user);
    }

    @Test
    public void givenNotExistingUser_findByLog_thenNotFoundException() {
        userEntity user = new userEntity("aaa","bbb","ccc","ddd","eee");
        given(repository.findByLogin(user.getLogin())).willReturn(null);

        try {
            cntr.findByLog(user.getLogin());
            fail("Should throw NotFoundException");
        } catch (NotFoundException ex) {
            assertThat(ex.getMessage(), is(equalTo(String.format("User with login [%s] not found.", user.getLogin()))));
        }
    }

    @Test
    public void givenExistingUser_findByLog_thenReturningString() {
        userEntity user = new userEntity("aaa","bbb","ccc","ddd","eee");
        given(repository.findByLogin(user.getLogin())).willReturn(user);

        String response = cntr.findByLog(user.getLogin());
        then(response).isEqualTo("The user have been already created");
    }


    @Test
    public void givenNotExistingUser_whenUpdateUser_thenNotFoundException() {
        userEntity user = new userEntity("aaa","bbb","ccc","ddd","eee");
        userEntity newuser = new userEntity();
        newuser.setLogin("login");

        given(repository.findByLogin(user.getLogin())).willReturn(null);

        try {
            cntr.update(user.getLogin(), newuser);
            fail("Should throw NotFoundException");
        } catch (NotFoundException ex) {
            assertThat(ex.getMessage(), is(equalTo(String.format("User with login [%s] not found.", user.getLogin()))));
        }
    }

    @Test
    public void givenExistingUser_AndExistingLogin_whenUpdateUser_thenWrongDataException() {
        userEntity user = new userEntity("aaa","bbb","ccc","ddd","eee");

        given(repository.findByLogin(user.getLogin())).willReturn(user);

        try {
            cntr.update(user.getLogin(), user);
            fail("Should throw WrongDataException");
        } catch (WrongDataException ex) {
            assertThat(ex.getMessage(), is(equalTo(String.format("User with login [%s] already exists.", user.getLogin()))));
        }
    }

    @Test
    public void givenExistingUser_AndNotExistingLogin__whenCheckUser_thenReturningUser() {
        userEntity user = new userEntity("aaa","bbb","ccc","ddd","eee");
        userEntity newuser = new userEntity("newLogin","fff","ggg","hhh","iii");
        user.setId(1);

        given(repository.findByLogin(user.getLogin())).willReturn(user);
        given(repository.findByLogin(newuser.getLogin())).willReturn(null);

        given(cntr.update(user.getLogin(),newuser)).willReturn(ResponseEntity.ok(user));
    }

    @Test
    public void givenNotExistingUser_DeleteUser_thenNotFoundException() {
        userEntity user = new userEntity("aaa","bbb","ccc","ddd","eee");

        given(repository.findByLogin(user.getLogin())).willReturn(null);

        try {
            cntr.delete(user.getLogin());
            fail("Should throw NotFoundException");
        } catch (NotFoundException ex) {
            assertThat(ex.getMessage(), is(equalTo(String.format("User with login [%s] not found.", user.getLogin()))));
        }
    }

   @Test
    public void givenExistingUser_DeleteUser_thenReturning() {
        userEntity user = new userEntity("aaa","bbb","ccc","ddd","eee");

        given(repository.findByLogin(user.getLogin())).willReturn(user);

        ResponseEntity response = cntr.delete(user.getLogin());
        then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(response.getBody()).isEqualTo("User Deleted");
    }

}