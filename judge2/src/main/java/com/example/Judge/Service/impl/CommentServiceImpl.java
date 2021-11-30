package com.example.Judge.Service.impl;

import com.example.Judge.Repository.CommentRepository;
import com.example.Judge.Service.CommentService;
import com.example.Judge.Service.HomeworkService;
import com.example.Judge.Service.UserService;
import com.example.Judge.model.Service.CommentServiceModel;
import com.example.Judge.model.entity.Comment;
import com.example.Judge.security.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final HomeworkService homeworkService;
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository, UserService userService, HomeworkService homeworkService, CurrentUser currentUser, ModelMapper modelMapper){
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.homeworkService = homeworkService;
        this.currentUser = currentUser;
        this.modelMapper = modelMapper;
    }

    @Override
    public void add(CommentServiceModel serviceModel, Long homeworkId){
        Comment comment = modelMapper
                .map(serviceModel, Comment.class);
        comment.setAuthor (userService.findById (currentUser.getId ()))
                .setHomework (homeworkService.findById(homeworkId));

        commentRepository.save (comment);

    }

    @Override
    public Double findAvgScore(){
        return commentRepository.findAvgScore ();
    }

    @Override
    public Map<Integer, Integer> findScoreMap(){
        Map<Integer, Integer> scoreMap = initScoreMap();
        commentRepository
                .findAll ()
                .forEach (comment -> {
                    Integer score = comment.getScore ();
                    scoreMap.put(score,scoreMap.get (score)+1);
                });
        
        
        return scoreMap;
    }

    @Override
    public List<String> findTopStudentList(){
        List<String> topStudent = new ArrayList<> ();
       List<Comment> sortedComment = commentRepository
                                        .findAll ()
                                        .stream ()
                                        .sorted ((o1, o2) -> o2.getScore ().compareTo (o1.getScore ()))
                                        .collect(Collectors.toList());
        for (int i = 0; i < sortedComment.size (); i++) {
            topStudent.add (sortedComment.get (i).getAuthor ().getUsername ());
        }
        return topStudent;

    }

    private Map<Integer, Integer> initScoreMap(){
        Map<Integer, Integer> map = new HashMap<> ();
        for (int i = 2; i <=6 ; i++) {
            map.put(i,0);
        }
        return map;
    }
}
