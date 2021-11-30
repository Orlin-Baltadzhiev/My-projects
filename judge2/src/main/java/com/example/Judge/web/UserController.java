package com.example.Judge.web;

import com.example.Judge.Service.UserService;
import com.example.Judge.model.Service.UserServiceModel;
import com.example.Judge.model.binding.UserLoginBindingModel;
import com.example.Judge.model.binding.UserRegisterBindingModel;
import com.example.Judge.security.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public UserController(UserService userService, ModelMapper modelMapper, CurrentUser currentUser){
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }


    @GetMapping("/login")
    public String login(Model model){
        if(!model.containsAttribute ("userLoginBindingModel")){
            model.addAttribute ("userLoginBindingModel", new UserLoginBindingModel ());
            model.addAttribute ("notFound", false);
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid @ModelAttribute UserLoginBindingModel userLoginBindingModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               HttpSession httpSession){
        if (bindingResult.hasErrors ()) {
            redirectAttributes.addFlashAttribute ("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute ("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
            return "redirect:login";
        }


        UserServiceModel user = userService.findUserByUsernameAndPassword (userLoginBindingModel.getUsername (),
                userLoginBindingModel.getPassword ());

        if(user == null){
            redirectAttributes.addFlashAttribute ("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute ("notFound", true);
           return "redirect:login";
        }
        userService.login(user);

//        httpSession.setAttribute ("user",user);
        return "redirect:/";


    }

    @GetMapping("/register")
    public String register(Model model){
        if (!model.containsAttribute ("userRegisterBindingModel")) {
            model.addAttribute ("userRegisterBindingModel", new UserRegisterBindingModel ());
        }

        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid
                                  @ModelAttribute UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors () || !userRegisterBindingModel.getPassword ().equals
                (userRegisterBindingModel.getConfirmPassword ())) {
            redirectAttributes.addFlashAttribute ("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute ("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            return "redirect:register";
        }
        UserServiceModel userServiceModel = modelMapper
                .map (userRegisterBindingModel, UserServiceModel.class);
        userService.registerUser (userServiceModel);

        return "redirect:login";
    }

    @GetMapping("/logout")
    public String logout(){
        userService.logout();
        return "redirect:/";
    }

    @GetMapping("/profile/{id}")
    public String profile(@PathVariable Long id, Model model){
        if(currentUser.isAnonymous ()){
            return "redirect:/login";
        }

        model.addAttribute ("user", userService.findProfileById(id));
        return "profile";
    }


}
