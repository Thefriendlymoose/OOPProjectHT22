package usertests;

import articletests.TestWMS;
import model.WMS;
import model.article.Article;
import model.article.ArticleCategory;
import model.article.ArticleStatus;
import model.article.Articles;
import model.user.Role;
import model.user.User;
import model.user.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestUser {

    private WMS wms;
    @BeforeEach
    public void init(){
        TestWMS t = new TestWMS();
        this.wms = t.getWMS();
    }

    @Test
    public void addUser(){
        Users users = wms.getUsers();
        int sizeBeforeAdd = users.getInList().size();
        users.addUser(new User(users.getNextUserID(), "test name", "1234", "Alexander h", true, Role.getAdmin()));
        int sizeAfterAdd = users.getInList().size();
        assertEquals(sizeAfterAdd - 1, sizeBeforeAdd);
    }

    @Test
    public void removeUser(){
        Users users = wms.getUsers();
        List<User> myUsers =  users.getAllUsers();
        int sizeBeforeRemove = users.getInList().size();
        users.removeUser(myUsers.get(0));
        int sizeAfterRemove = users.getInList().size();
        assertEquals(sizeAfterRemove + 1 , sizeBeforeRemove);
    }

    @Test
    public void getUserByName(){
        Users users = wms.getUsers();
        assertEquals(users.getUserById(3L), users.returnUserByUsername("b"));
    }

    @Test
    public void getNextID(){
        Users users = wms.getUsers();
        List<User> myUsers =  users.getAllUsers();
        Long id = users.getNextUserID();
        users.addUser(new User(id, "test name", "1234", "Alexander h", true, Role.getAdmin()));
        assertEquals(id+1, users.getNextUserID());
    }

    @Test
    public void getFirstOrLastName(){
        Users users = wms.getUsers();
        List<User> myUsers =  users.getAllUsers();
        User u1 = myUsers.get(0);
        String u1LastName = u1.getLastName(u1.getName());
        u1.getFirstName(u1.getName());
        String u1FirstName = u1.getFirstName(u1.getName());
        assertEquals(u1FirstName + " " + u1LastName, u1.getName());
    }


    @Test
    public void removeUserTest(){
        Users users = wms.getUsers();
        int usersBeforeRemove = users.getAllUsers().size();
        User user1 = users.getUserById(1L);
        users.removeUser(user1);
        User user2 = users.getUserById(1L);
        int usersAfterRemove = users.getAllUsers().size();
        assertEquals(usersBeforeRemove - 1, usersAfterRemove);
        assertTrue(user2 == null);
    }




}
