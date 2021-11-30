package com.example.Judge.web;

import com.example.Judge.Service.CommentService;
import com.example.Judge.Service.ExerciseService;
import com.example.Judge.Service.UserService;
import com.example.Judge.security.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class HomeController {
    private final CurrentUser currentUser;
    private final ExerciseService exerciseService;
    private final CommentService commentService;
    private final UserService userService;

    public HomeController(CurrentUser currentUser, ExerciseService exerciseService, CommentService commentService, UserService userService){
        this.currentUser = currentUser;
        this.exerciseService = exerciseService;
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model){

       if(currentUser.isAnonymous ()){
           return "index";
       }

       model.addAttribute ("exercises", exerciseService.findAllExerciseNames ());
       model.addAttribute ("avg",commentService.findAvgScore());
       model.addAttribute ("userCount",userService.findUsersCount());
       model.addAttribute ("scoreMap",commentService.findScoreMap());
       model.addAttribute ("topStudents",commentService.findTopStudentList());

       return "home";
    }
}
