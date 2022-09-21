package com.ideas2it.employee.model;

/**
 * <h1> Role </h1>
 * <p>
 * It has getter, setter methods
 * </p>
 * version 1.0
 * @author ruban 11/08/22
 **/
public class Role {
    private int roleId;
    private String description;

    public Role() {
    }

    public Role(String description) {
        this.description = description;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

    