package cn.xtrui.database.bean;

import java.util.Vector;

public class Role {
    private Integer id;
    private String info;
    private String name;
    private Vector<Permission> permissions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", info='" + info + '\'' +
                ", name='" + name + '\'' +
                ", permissions=" + permissions +
                '}';
    }

    public Vector<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Vector<Permission> permissions) {
        this.permissions = permissions;
    }
}
