package com.example.Judge.model.view;

public class HomeworkViewModel {

    private Long id;
    private String gitAddress;

    public HomeworkViewModel(){
    }

    public Long getId(){
        return id;
    }

    public HomeworkViewModel setId(Long id){
        this.id = id;
        return this;
    }

    public String getGitAddress(){
        return gitAddress;
    }

    public HomeworkViewModel setGitAddress(String gitAddress){
        this.gitAddress = gitAddress;
        return this;
    }
}
