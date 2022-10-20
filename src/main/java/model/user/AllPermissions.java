package model.user;

//TODO WILL REPLACE THIS WITH A ROLE CLASS

import java.util.ArrayList;
import java.util.List;

public final class AllPermissions {
    private AllPermissions() {
    }
    public static List<Permission> managerPermissions(){
        List<Permission> perms = new ArrayList<>(){
            {add(Permission.ARTICLE);add(Permission.SITE);}};
        return perms;
    }
}
