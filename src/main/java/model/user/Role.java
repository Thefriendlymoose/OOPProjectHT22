package model.user;

import java.util.ArrayList;
import java.util.List;

/**
 * Role class
 */
public class Role {
    private String description;
    private String name;
    private List<Permission> permissions = new ArrayList<>();

    /**
     * empty constructor for the static funtions
     */
    private Role(){

    }

    /**
     * Constructor for role, used when deserializing
     * @param description the role description
     * @param name the name of the role
     * @param permissions roles accesses to the program
     */
    public Role(String description, String name, List<Permission> permissions) {
        this.description = description;
        this.name = name;
        this.permissions = permissions;
    }

    /**
     * Static funtion that makes and returns the manager role
     * @return the manager role
     */

    public static Role getManager(){
        Role role = new Role();
        role.name = "manager";
        role.description = "Access to Orders, Users and Sites";
        role.permissions.add(Permission.ORDER);
        role.permissions.add(Permission.USER);
        role.permissions.add(Permission.SITE);
        return role;
    }
    /**
     * Static funtion that makes and returns the admin role
     * @return the admin role
     */
    public static Role getAdmin(){
        Role role = new Role();
        role.name = "admin";
        role.description = "Access to all parts of the program";
        role.permissions.add(Permission.ALL);;
        return role;
    }
    /**
     * Static funtion that makes and returns the salesPerson role
     * @return the salesPersonRole role
     */
    public static Role getSalesPerson(){
        Role role = new Role();
        role.name = "salesperson";
        role.description = "Access to Articles, Orders and Customers";
        role.permissions.add(Permission.CUSTOMER);
        role.permissions.add(Permission.ARTICLE);
        role.permissions.add(Permission.ORDER);
        return role;
    }


    /**
     * Checks if the role has the rquired permission
     * @param permission to be checked
     * @return true if role has permission and false if not
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
