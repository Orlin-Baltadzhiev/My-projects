package com.example.Judge.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="users")
public class User extends BaseEntity {

    private String username;
    private String password;
    private String email;
    private String git;
    private Role role;



    private Set<Homework> homeworkSet;

    public User() {
    }

    @Column(name = "username", unique = true,nullable = false)
    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }
    @Column(name = "password" ,nullable = false)
    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }
    @Column(name = "email", unique = true, nullable = false)
    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    @Column(name = "git")
    public String getGit() {
        return git;
    }

    public User setGit(String git) {
        this.git = git;
        return this;
    }

    @ManyToOne
    public Role getRole() {
        return role;
    }

    public User setRole(Role role) {
        this.role = role;
        return this;
    }

    @OneToMany(mappedBy = "author",fetch = FetchType.EAGER)
    public Set<Homework> getHomeworkSet(){
        return homeworkSet;
    }

    public User setHomeworkSet(Set<Homework> homeworkSet){
        this.homeworkSet = homeworkSet;
        return this;
    }
}
