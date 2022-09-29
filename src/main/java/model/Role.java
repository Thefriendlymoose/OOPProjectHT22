package model;

import java.util.List;

//To implement later
public class Role {
    private String description;
    private String name;
    private List<Permission> permissions;
    public Role(){

    }
    public static Role getManager(){
        Role role = new Role();
        role.name = "Manager";
        role.description = "asdf";
        role.permissions.add(Permission.ARTICLE);
        return role;
    }

    public static Role getAdmin(){
        Role role = new Role();
        role.name = "Manager";
        role.description = "asdf";
        role.permissions.add(Permission.ALL);
        return role;
    }
    public static Role getSalesPerson(){
        Role role = new Role();
        role.name = "SalesPerson";
        role.description = "asdf";
        role.permissions.add(Permission.CUSTOMER);
        return role;
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
}
