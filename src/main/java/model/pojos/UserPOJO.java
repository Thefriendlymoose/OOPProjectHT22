package model.pojos;
import model.Roles;

public class UserPOJO {
    private String userName;
    private String passWord;
    private Roles role;
    public UserPOJO(String userName, String passWord, Roles role) {
        this.userName = userName;
        this.passWord = passWord;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}
