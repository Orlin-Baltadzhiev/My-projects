package com.example.Judge.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "homework")
public class Homework extends BaseEntity {

    private LocalDateTime addedOn;
    private String gitAddress;
    private User author;
    private Exercise exercise;
    private Set<Comment> comments;

    public Homework() {
    }

    @Column(name = "added_on")
    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public Homework setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
        return this;
    }
    @Column(name = "git_adress",nullable = false)
    public String getGitAddress() {
        return gitAddress;
    }

    public Homework setGitAddress(String gitAddress) {
        this.gitAddress = gitAddress;
        return this;
    }

    @ManyToOne
    public User getAuthor() {
        return author;
    }

    public Homework setAuthor(User author) {
        this.author = author;
        return this;
    }
    @ManyToOne //TODO unidirectional -> bidirectional
    public Exercise getExercise() {
        return exercise;
    }

    public Homework setExercise(Exercise exercise) {
        this.exercise = exercise;
        return this;
    }

    @OneToMany(mappedBy = "homework",fetch = FetchType.EAGER)
    public Set<Comment> getComments(){
        return comments;
    }

    public Homework setComments(Set<Comment> comments){
        this.comments = comments;
        return this;
    }
}
