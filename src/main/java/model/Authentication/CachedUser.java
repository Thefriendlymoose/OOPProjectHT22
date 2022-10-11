package model.Authentication;

import model.Permission;
import model.User;

public class CachedUser {

    private User user;

    public CachedUser(User user){
        this.user = user;
    }

    public boolean hasPermission(Permission permission){
        return user.hasPermission(permission);
    }
}
