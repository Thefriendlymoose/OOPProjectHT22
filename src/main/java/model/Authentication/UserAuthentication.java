package model.Authentication;

import model.Permission;
import model.User;

import java.util.Map;

public class UserAuthentication {
    private Map<Long, User> users;
    private User currentUser;

    public UserAuthentication(Map<Long, User> users){
        this.users = users;
    }

    public AuthenticationStatus logIn(User user){
        if (currentUser == null){
            if (!users.containsKey(user.getUserId())){
                return AuthenticationStatus.USER_NOT_FOUND;
            } else if (users.get(user.getUserId()).isPasswordCorrect(user)){
                currentUser = user;
                return AuthenticationStatus.SUCCESS;
            }
            return AuthenticationStatus.INCORRECT_PASSWORD;
        } else {
            return AuthenticationStatus.ALREADY_USER_LOGGED_IN;
        }
    }

    public AuthenticationStatus logOut(){
        if (currentUser == null) {
            return AuthenticationStatus.NO_CURRENT_USER;
        } else {
            currentUser = null;
            return AuthenticationStatus.SUCCESS;
        }
    }

    public CachedUser getCachedUser(){
        return new CachedUser(currentUser);
    }
}
