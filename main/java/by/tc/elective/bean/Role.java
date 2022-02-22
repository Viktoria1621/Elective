package by.tc.elective.bean;

import java.io.Serializable;
import java.util.Objects;

public class Role implements Serializable {
    private int roleID;
    private String roleTitle;

    public Role() {

    }

    public Role(String roleTitle) {
        this.roleTitle = roleTitle;
    }

    public Role(int roleID, String roleTitle) {
        this.roleID = roleID;
        this.roleTitle = roleTitle;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getRoleTitle() {
        return roleTitle;
    }

    public void setRoleTitle(String roleTitle) {
        this.roleTitle = roleTitle;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleID=" + roleID +
                ", roleTitle='" + roleTitle + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return roleID == role.roleID && roleTitle.equals(role.roleTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleID, roleTitle);
    }
}
