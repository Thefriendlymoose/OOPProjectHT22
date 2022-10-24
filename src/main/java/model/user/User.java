package model.user;

import model.observer.Observable;
import model.observer.Observer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * User class
 * @author Alexander Hyyppä
 */

public class User {

    private long userId;
    private String userName;
    private String password;
    private String name;
    private boolean status;
    private Role role;
    /**
     * User Constructor
     * @param userId the id of the user
     * @param userName
     * @param password
     * @param name
     * @param status
     * @param role
     */

    public User(long userId, String userName, String password, String name, boolean status, Role role) {
        this.userId = checkNull("UserID is Null",userId);
        this.userName = checkNull("UserName",userName);
        this.password = checkNull("Password is null" ,password);
        this.name = checkNull("Name",name);
        this.status = status;
        this.role = checkNull("Role" ,role);
    }

    /**
     *
     * @param message to be displayed when object is null
     * @param object to be checked if null
     * @param <T> the generic type
     * @return NullpointerException
     */
    private <T> T checkNull(String message, T object){
        if (object == null){
            throw new NullPointerException(message + "is Null");
        }
        return object;
    }

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

    public Boolean[] getAllStatus() {
        return new Boolean[]{true,false};
    }




    public void setStatus(boolean status) {
        this.status = status;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role permissions) {
        this.role = permissions;
    }


    /**
     *
     * @param name to get the first name out of
     * @return first Name
     */
    public String getFirstName(String name) {return getFirstOrLastName(name,0);}

    /**
     *
     * @param name to get the last name out of
     * @return last name
     */
    public String getLastName(String name) {return getFirstOrLastName(name,1);}

    /**
     * A method that splits a name into last and first name
     * @param name to be split into first and last name
     * @param i gets the first or last name out of list
     * @return
     */

    public String getFirstOrLastName(String name, int i){
        String[] nameList = name.split(" ");

        String firstOrLastName =nameList[i];
        return firstOrLastName;
    };






}
