package persistence.pojos;

import model.user.Permission;
import model.user.Role;

import java.util.List;

/**
 * User POJO data class used temporarily when deserializing a User from JSON
 */
public class UserJSON {

    private long userId;
    private String userName;
    private String password;
    private String name;
    private boolean status;
    private Role role;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


}
