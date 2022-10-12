package model.Authentication;


import model.user.User;

import java.util.List;

public class UserAuthentication {

    private Session session;

    public AuthenticationStatus logIn(String userName, String passWord, List<User> users){
        User user = null;
        for (User u : users){
            if (u.getUserName().equals(userName)){
                user = u;
            }
        }

        if (session != null){
            return AuthenticationStatus.ALREADY_USER_LOGGED_IN;
        } else if (user == null){
            return AuthenticationStatus.USER_NOT_FOUND;
        } else if (!user.getPassword().equals(passWord)){
            return AuthenticationStatus.INCORRECT_PASSWORD;
        } else {
            this.session = new Session(user);
            return AuthenticationStatus.SUCCESS;
        }
    }

    public AuthenticationStatus logOut(){
        if (session == null){
            return AuthenticationStatus.NO_CURRENT_USER;
        } else {
            session = null;
            return AuthenticationStatus.SUCCESS;
        }
    }

    public Session getSession(){
        return session;
    }

}
