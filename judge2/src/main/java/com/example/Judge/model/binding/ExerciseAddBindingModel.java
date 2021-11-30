package com.example.Judge.model.binding;


import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class ExerciseAddBindingModel {

    private String name;
    private LocalDateTime startedOn;
    private LocalDateTime dueDate;

    public ExerciseAddBindingModel(){
    }

    @Size(min = 2, message = "Name must be minimum two characters")
    public String getName(){
        return name;
    }

    public ExerciseAddBindingModel setName(String name){
        this.name = name;
        return this;
    }
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @PastOrPresent(message = "The date cannot be in the future")
    public LocalDateTime getStartedOn(){
        return startedOn;
    }

    public ExerciseAddBindingModel setStartedOn(LocalDateTime startedOn){
        this.startedOn = startedOn;
        return this;
    }
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @FutureOrPresent(message = "The date cannot be in the past")
    public LocalDateTime getDueDate(){
        return dueDate;
    }

    public ExerciseAddBindingModel setDueDate(LocalDateTime dueDate){
        this.dueDate = dueDate;
        return this;
    }
}
