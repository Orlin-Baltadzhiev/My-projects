package com.example.Judge.model.binding;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class HomeworkAddBindingModel {

    private String exercise;
    private String gitAddress;

    public HomeworkAddBindingModel(){
    }

    @Size(min = 2)
    public String getExercise(){
        return exercise;
    }

    public HomeworkAddBindingModel setExercise(String exercise){
        this.exercise = exercise;
        return this;
    }

    @Pattern(regexp = "https:\\/\\/github\\.com\\/.+",
            message = "Enter valid github address")
    public String getGitAddress(){
        return gitAddress;
    }

    public HomeworkAddBindingModel setGitAddress(String gitAddress){
        this.gitAddress = gitAddress;
        return this;
    }
}
