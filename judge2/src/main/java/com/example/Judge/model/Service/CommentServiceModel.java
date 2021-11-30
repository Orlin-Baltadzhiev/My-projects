package com.example.Judge.model.Service;

import com.example.Judge.model.entity.User;

public class CommentServiceModel {
    private Integer score;
    private String textContent;
    private UserServiceModel author;

    public CommentServiceModel(){
    }

    public Integer getScore(){
        return score;
    }

    public CommentServiceModel setScore(Integer score){
        this.score = score;
        return this;
    }

    public String getTextContent(){
        return textContent;
    }

    public CommentServiceModel setTextContent(String textContent){
        this.textContent = textContent;
        return this;
    }

    public UserServiceModel getAuthor(){
        return author;
    }

    public CommentServiceModel setAuthor(UserServiceModel author){
        this.author = author;
        return this;
    }
}
