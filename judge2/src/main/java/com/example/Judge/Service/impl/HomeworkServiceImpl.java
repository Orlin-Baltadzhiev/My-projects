package com.example.Judge.Service.impl;

import com.example.Judge.Repository.HomeworkRepository;
import com.example.Judge.Service.ExerciseService;
import com.example.Judge.Service.HomeworkService;
import com.example.Judge.Service.UserService;
import com.example.Judge.model.Service.HomeworkServiceModel;
import com.example.Judge.model.entity.Homework;
import com.example.Judge.security.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class HomeworkServiceImpl  implements HomeworkService {

    private final HomeworkRepository homeworkRepository;
    private final ExerciseService exerciseService;
    private final CurrentUser currentUser;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public HomeworkServiceImpl(HomeworkRepository homeworkRepository, ExerciseService exerciseService, CurrentUser currentUser, UserService userService, ModelMapper modelMapper){
        this.homeworkRepository = homeworkRepository;
        this.exerciseService = exerciseService;
        this.currentUser = currentUser;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addHomework(String exercise, String gitAddress){
        Homework homework = new Homework ();
        homework
                .setGitAddress (gitAddress)
                .setAddedOn (LocalDateTime.now ())
                .setExercise (exerciseService.findByName(exercise))
                .setAuthor (userService.findById(currentUser.getId ()));

        homeworkRepository.save (homework);
    }

    @Override
    public HomeworkServiceModel findHomeworkForScoring(){
        return homeworkRepository
                .findTop1ByOrderByComments ()
                .map (homework -> modelMapper
                        .map (homework, HomeworkServiceModel.class))
                .orElse (null);

    }

    @Override
    public Homework findById(Long homeworkId){
        return homeworkRepository
                .findById (homeworkId)
                .orElse (null);
    }
}
