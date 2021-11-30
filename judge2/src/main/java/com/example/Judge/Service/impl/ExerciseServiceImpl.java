package com.example.Judge.Service.impl;

import com.example.Judge.Repository.ExerciseRepository;
import com.example.Judge.Service.ExerciseService;
import com.example.Judge.model.Service.ExerciseServiceModel;
import com.example.Judge.model.entity.Exercise;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ModelMapper modelMapper;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, ModelMapper modelMapper){
        this.exerciseRepository = exerciseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addEx(ExerciseServiceModel exerciseServiceModel){
        exerciseRepository
                .save (modelMapper
                        .map(exerciseServiceModel, Exercise.class));

    }

    @Override
    public List<String> findAllExerciseNames(){
         return exerciseRepository
                 .findAllExNames ();
    }

    @Override
    public boolean checkIsLate(String exercise){
       Exercise exerciseEntity = exerciseRepository.
               findByName (exercise)
               .orElse (null);


        return exerciseEntity.getDueDate ().isBefore (LocalDateTime.now ());

    }

    @Override
    public Exercise findByName(String name){
        return exerciseRepository.findByName (name).orElse (null);
    }
}
