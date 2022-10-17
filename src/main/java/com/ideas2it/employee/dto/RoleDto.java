package com.ideas2it.employee.dto;

/**
 * <h1> Role </h1>
 * <p>
 * It has getter, setter methods
 * </p>
 * version 1.0
 * @author ruban 11/08/22
 **/

public class RoleDto {
    private int roleId;

    private String description;

    public RoleDto() {
    }

    public RoleDto(String description) {
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

    @Override
    public String toString () {
        return description;
    }
}
