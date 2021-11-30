package com.example.Judge.model.view;


import java.util.Set;

public class UserProfileViewModel {

    private String username;
    private Set<String> homeworkSet;
    private String email;
    private String git;

    public UserProfileViewModel(){
    }

    public String getUsername(){
        return username;
    }

    public UserProfileViewModel setUsername(String username){
        this.username = username;
        return this;
    }

    public Set<String> getHomeworkSet(){
        return homeworkSet;
    }

    public UserProfileViewModel setHomeworkSet(Set<String> homeworkSet){
        this.homeworkSet = homeworkSet;
        return this;
    }

    public String getEmail(){
        return email;
    }

    public UserProfileViewModel setEmail(String email){
        this.email = email;
        return this;
    }

    public String getGit(){
        return git;
    }

    public UserProfileViewModel setGit(String git){
        this.git = git;
        return this;
    }
}
