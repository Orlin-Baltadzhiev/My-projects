package com.example.Judge.Service.impl;

import com.example.Judge.Repository.RoleRepository;
import com.example.Judge.Service.RoleService;
import com.example.Judge.model.entity.Role;
import com.example.Judge.model.entity.RoleNameEnum;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void initRoles() {
        if(roleRepository.count() == 0) {
            Role admin = new Role(RoleNameEnum.ADMIN);
            Role user = new Role(RoleNameEnum.USER);

            roleRepository.save(admin);
            roleRepository.save(user);

        }
    }

    @Override
    public Role findRole(RoleNameEnum roleNameEnum){
        return roleRepository
                .findByName (roleNameEnum)
                .orElse (null);
    }
}
