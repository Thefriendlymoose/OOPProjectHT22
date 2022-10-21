package model.user;

import java.util.List;

//To implement later
public class Role {
    private String description;
    private String name;
    private List<Permission> permissions;

    public Role(String description, String name, List<Permission> permissions) {
        this.description = description;
        this.name = name;
        this.permissions = permissions;
    }

    public void getManager(){
        this.name = "Manager";
        this.description = "asdf";
        this.permissions.add(Permission.ARTICLE);
    }

    public Role getAdmin(){
        this.name = "admin";
        this.description = "asdf";
        this.permissions.add(Permission.ALL);;
        return this;
    }
    public void getSalesPerson(){
        this.name = "SalesPerson";
        this.description = "asdf";
        this.permissions.add(Permission.CUSTOMER);
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
}
