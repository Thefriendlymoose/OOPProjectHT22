package model.authentication;


import model.user.User;
import model.user.Users;

import java.util.List;

/**
 * Class is used to log in/out user from the system
 */
public class UserAuthentication {

    private Session session;

    /**
     * Method used when you want to log in to the system
     * @param userName takes a username
     * @param passWord password
     * @param users and a list of users
     * @return returns status of log in attempt
     */
    public AuthenticationStatus logIn(String userName, String passWord, Users users){

        User user = users.returnUserByUsername(userName);

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


    /**
     * Method used when trying to log out
     * @return status of log out attempt, if no session is active it means that user is not logged in
     *          If session active, session is deleted.
     */
    public AuthenticationStatus logOut(){
        if (session == null){
            return AuthenticationStatus.NO_CURRENT_USER;
        } else {
            session = null;
            return AuthenticationStatus.SUCCESS;
        }
    }

    /**
     *
     * @return returns session
     */
    public Session getSession(){
        return session;
    }

}
