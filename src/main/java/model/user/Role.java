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
        role.name = "Manager";
        role.description = "asdf";
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
        role.name = "SalesPerson";
        role.description = "Access to Article";
        role.permissions.add(Permission.CUSTOMER);
        role.permissions.add(Permission.ARTICLE);
        role.permissions.add(Permission.ORDER);
        return role;
    }


    /*
    public Role getManager(){
        this.name = "Manager";
        this.description = "asdf";
        this.permissions.add(Permission.ORDER);
        this.permissions.add(Permission.USER);
        this.permissions.add(Permission.SITE);
        return this;

    }

    public Role getAdmin(){
        this.name = "admin";
        this.description = "Access to all parts of the program";
        this.permissions.add(Permission.ALL);;
        return this;
    }
    public Role getSalesPerson(){
        this.name = "SalesPerson";
        this.description = "Access to Article";
        this.permissions.add(Permission.CUSTOMER);
        this.permissions.add(Permission.ARTICLE);
        this.permissions.add(Permission.ORDER);
        return this;
    }

     */

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
