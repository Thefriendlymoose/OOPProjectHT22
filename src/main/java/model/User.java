package model;

import com.google.gson.Gson;

import java.util.List;

//User(1,"Olofsson","Olof321", "Olof", [USER], true)
public class User {

    private int userId;
    private String userName;
    private String password;
    private String name;
    private boolean status;
    private List<Permission> permissions;

    public User(int userId, String userName, String password, String name, boolean status, List<Permission> permissions) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.status = status;
        this.permissions = permissions;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
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

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    private Boolean allowed(Permission perm){
        if (this.permissions.contains(Permission.ALL))
            return true;
        return  (this.permissions.contains(perm));
    }
    public Boolean allowedUser(){
        return allowed(Permission.USER);
    }
    public Boolean allowedCustomer(){
        return allowed(Permission.CUSTOMER);
    }

    public Boolean allowedSite(){
        return allowed(Permission.SITE);
    }

    public Boolean allowedOrder(){
        return allowed(Permission.ORDER);
    }
    public Boolean allowedArticle(){
        return allowed(Permission.ARTICLE);
    }


}
