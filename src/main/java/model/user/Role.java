package model.user;

import java.util.ArrayList;
import java.util.List;

//To implement later
public class Role {
    private String description;
    private String name;
    private List<Permission> permissions = new ArrayList<>();

    private Role(){

    }

    public Role(String description, String name, List<Permission> permissions) {
        this.description = description;
        this.name = name;
        this.permissions = permissions;
    }

    public static Role getManager(){
        Role role = new Role();
        role.name = "manager";
        role.description = "Access to Orders, Users and Sites";
        role.permissions.add(Permission.ORDER);
        role.permissions.add(Permission.USER);
        role.permissions.add(Permission.SITE);
        return role;
    }
    public static Role getAdmin(){
        Role role = new Role();
        role.name = "admin";
        role.description = "Access to all parts of the program";
        role.permissions.add(Permission.ALL);;
        return role;
    }
    public static Role getSalesPerson(){
        Role role = new Role();
        role.name = "salesperson";
        role.description = "Access to Articles, Orders and Customers";
        role.permissions.add(Permission.CUSTOMER);
        role.permissions.add(Permission.ARTICLE);
        role.permissions.add(Permission.ORDER);
        return role;
    }




    public boolean hasPermission(Permission permission){
        if (permission == Permission.ALL)
            return true;
        return permissions.contains(permission);
    }


    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    @Override
    public String toString() {
        return name;
    }
}
