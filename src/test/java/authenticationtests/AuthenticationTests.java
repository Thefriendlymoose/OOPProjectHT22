package authenticationtests;

import model.authentication.AuthenticationStatus;
import model.authentication.UserAuthentication;
import model.user.Role;
import model.user.User;
import model.user.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static com.mongodb.internal.connection.tlschannel.util.Util.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthenticationTests {

    private UserAuthentication ua;
    private Users users;

    @BeforeEach
    public void init(){
        this.ua = new UserAuthentication();
        Users users = new Users(new HashMap<>());
        User user = new User(users.getNextUserID(), "testuser", "1234", "test name", true, Role.getAdmin());
        users.addUser(user);
        this.users = users;
    }

    @Test
    public void logInSuccessTest(){
        AuthenticationStatus status = ua.logIn("testuser", "1234", users);
        assertEquals(status, AuthenticationStatus.SUCCESS);
        assertTrue(ua.getSession() != null);
    }

    @Test
    public void logInFailWrongPassWord(){
        AuthenticationStatus status = ua.logIn("testuser", "fel", users);
        assertEquals(status, AuthenticationStatus.INCORRECT_PASSWORD);
    }

    @Test
    public void logInFailUserNotExist(){
        AuthenticationStatus status = ua.logIn("test", "fel", users);
        assertEquals(status, AuthenticationStatus.USER_NOT_FOUND);
    }

    @Test
    public void logInFailUserAlreadyLoggedIn(){
        AuthenticationStatus status = ua.logIn("testuser", "1234", users);
        AuthenticationStatus status2 = ua.logIn("testuser", "1234", users);
        assertEquals(status, AuthenticationStatus.SUCCESS);
        assertEquals(status2, AuthenticationStatus.ALREADY_USER_LOGGED_IN);
        assertTrue(ua.getSession() != null);
    }

    @Test
    public void logOutSuccess(){
        AuthenticationStatus status = ua.logIn("testuser", "fel", users);
        AuthenticationStatus status2 = ua.logOut();
        assertEquals(status, AuthenticationStatus.SUCCESS);
        assertEquals(status2, AuthenticationStatus.SUCCESS);
        assertTrue(ua.getSession() == null);
    }

    @Test
    public void logOutFailure(){
        AuthenticationStatus status = ua.logOut();
        assertEquals(status, AuthenticationStatus.NO_CURRENT_USER);
        assertTrue(ua.getSession() == null);
    }


}
