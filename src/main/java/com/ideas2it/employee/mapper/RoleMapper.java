package com.ideas2it.employee.mapper;

import com.ideas2it.employee.dto.RoleDto;
import com.ideas2it.employee.model.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public static Role toRole(RoleDto roleDto) {
        Role role = new Role();
        role.setDescription(roleDto.getDescription());
        return role;
    }

    public static RoleDto toRoleDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setDescription(role.getDescription());
        return roleDto;
    }
}
