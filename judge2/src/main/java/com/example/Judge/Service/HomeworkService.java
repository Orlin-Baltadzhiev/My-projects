package com.example.Judge.Service;

import com.example.Judge.model.Service.HomeworkServiceModel;
import com.example.Judge.model.entity.Homework;

public interface HomeworkService {
    void addHomework(String exercise, String gitAddress);

    HomeworkServiceModel findHomeworkForScoring();

    Homework findById(Long homeworkId);
}
