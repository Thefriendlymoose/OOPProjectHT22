package model.user;

import java.util.ArrayList;
import java.util.List;

public class User {

    private long userId;
    private String userName;
    private String password;
    private String name;
    private boolean status;
    private List<Permission> permissions;

    public User(long userId, String userName, String password, String name, boolean status, List<Permission> permissions) {
        this.userId = checkNull("UserID is Null",userId);
        this.userName = checkNull("UserName",userName);
        this.password = checkNull("Password is null" ,password);
        this.name = checkNull("Name",name);
        this.status = status;
        this.permissions = checkNull("permissions" ,permissions);
    }
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

    public  List<Permission> managerPermissions(){
        List<Permission> perms = new ArrayList<>(){
            {add(Permission.ARTICLE);add(Permission.SITE);add(Permission.ORDER);
                add(Permission.CUSTOMER);add(Permission.USER);}};
        return perms;
    }


    public  List<Permission> normalPermissions(){
        List<Permission> perms = new ArrayList<>(){
            {add(Permission.SITE);add(Permission.ORDER);
                add(Permission.USER);}};
        return perms;
    }
    public  List<Permission> adminPermissions(){
        List<Permission> perms = new ArrayList<>(){
            {add(Permission.ALL);}};
        return perms;
    }

    public List <List<Permission>> getAllPermissions(){
        List <List <Permission>> perms = new ArrayList<>(){
            {add(normalPermissions());add(adminPermissions());
                add(managerPermissions());}};

        return perms;
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

    public String getFirstName(String name) {return getFirstOrLastName(name,0);}
    public String getLastName(String name) {return getFirstOrLastName(name,1);}

    public String getFirstOrLastName(String name, int i){
        String[] nameList = name.split(" ");

        String firstOrLastName =nameList[i];
        return firstOrLastName;
    };


    public boolean isPasswordCorrect(User user){
        return user.getPassword().equals(this.password);
    }

    public boolean hasPermission(Permission permission){
        return permissions.contains(permission);
    }



}
