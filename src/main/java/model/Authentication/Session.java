package model.Authentication;

import model.user.Permission;
import model.user.User;

public class Session {
    private User user;

    public Session(User user){
        this.user = user;
    }

    public boolean hasAccess(Permission permission){
        return user.hasPermission(permission);
    }

}
