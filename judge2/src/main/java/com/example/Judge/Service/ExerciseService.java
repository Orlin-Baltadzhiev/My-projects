package com.example.Judge.Service;

import java.util.List;

import com.example.Judge.model.Service.ExerciseServiceModel;
import com.example.Judge.model.entity.Exercise;

public interface ExerciseService {

    void addEx(ExerciseServiceModel exerciseServiceModel);

    List<String> findAllExerciseNames();

    boolean checkIsLate(String exercise);

    Exercise findByName(String exercise);
}
