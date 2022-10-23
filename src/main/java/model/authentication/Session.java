package model.authentication;

import model.user.Permission;
import model.user.User;

/**
 * When a user has logged in a session is created.
 *
 * @author David al Amiri
 */
public class Session {
    private User user;

    public Session(User user){
        this.user = user;
    }

    public boolean hasAccess(Permission permission){
        return user.hasPermission(permission);
    }

    public User getUser() {
        return user;
    }
}
