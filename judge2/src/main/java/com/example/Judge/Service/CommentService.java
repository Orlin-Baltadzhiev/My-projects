package com.example.Judge.Service;

import com.example.Judge.model.Service.CommentServiceModel;

import java.util.Map;
import java.util.List;

public interface CommentService {
    void add(CommentServiceModel serviceModel, Long homeworkId);

    Double findAvgScore();

    Map<Integer, Integer> findScoreMap();

    List<String> findTopStudentList();
}
