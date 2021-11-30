package com.example.Judge.web;

import com.example.Judge.Service.ExerciseService;
import com.example.Judge.model.Service.ExerciseServiceModel;
import com.example.Judge.model.binding.ExerciseAddBindingModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;
    private final ModelMapper modelMapper;

    public ExerciseController(ExerciseService exerciseService, ModelMapper modelMapper){
        this.exerciseService = exerciseService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String add(Model model){
        if(!model.containsAttribute ("exerciseAddBindingModel")){
            model.addAttribute ("exerciseAddBindingModel", new ExerciseAddBindingModel ());
        }
        return "exercise-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid ExerciseAddBindingModel exerciseAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors ()){
            redirectAttributes.addFlashAttribute ("exerciseAddBindingModel", exerciseAddBindingModel);
            redirectAttributes.addFlashAttribute ("org.springframework.validation.BindingResult.exerciseAddBindingModel", bindingResult);
            return "redirect:add";
        }
        exerciseService.addEx(modelMapper.map (exerciseAddBindingModel, ExerciseServiceModel.class));
        return "redirect:/";

    }


}
