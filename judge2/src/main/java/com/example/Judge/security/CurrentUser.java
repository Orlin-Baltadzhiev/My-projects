package com.example.Judge.security;

import com.example.Judge.model.entity.RoleNameEnum;
import org.springframework.stereotype.Component;

@Component
public class CurrentUser {

    private Long id;
    private String username;
    private RoleNameEnum role;


    public CurrentUser(){
    }

    public Long getId(){
        return id;
    }

    public CurrentUser setId(Long id){
        this.id = id;
        return this;
    }

    public String getUsername(){
        return username;
    }

    public CurrentUser setUsername(String username){
        this.username = username;
        return this;
    }

    public boolean isAnonymous(){
        return this.username == null;
    }

    public RoleNameEnum getRole(){
        return role;
    }

    public CurrentUser setRole(RoleNameEnum role){
        this.role = role;
        return this;
    }

    public boolean isAdmin(){
        return this.role == RoleNameEnum.ADMIN;
    }
}
