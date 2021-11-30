package com.example.Judge.Service;


import com.example.Judge.model.entity.Role;
import com.example.Judge.model.entity.RoleNameEnum;

public interface RoleService  {
    void initRoles();

    Role findRole(RoleNameEnum roleNameEnum);
}
