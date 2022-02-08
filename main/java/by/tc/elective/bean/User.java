package by.tc.elective.bean;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private int userID;
    private String login;
    private String password;
    private String name;
    private int roleID;

    public User() {

    }

    public User(String login, String password, String name, int roleID) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.roleID = roleID;
    }

    public User(int userID, String login, String password, String name, int roleID) {
        this.userID = userID;
        this.login = login;
        this.password = password;
        this.name = name;
        this.roleID = roleID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public boolean isValid() {
        return userID != 0 && login != null && password != null && name != null && roleID != 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", roleID=" + roleID +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return userID == user.userID && roleID == user.roleID && login.equals(user.login) && password.equals(user.password) && name.equals(user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, login, password, name, roleID);
    }
}
