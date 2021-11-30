package com.example.Judge.Service.impl;

import com.example.Judge.Repository.UserRepository;
import com.example.Judge.Service.RoleService;
import com.example.Judge.Service.UserService;
import com.example.Judge.model.Service.UserServiceModel;
import com.example.Judge.model.entity.RoleNameEnum;
import com.example.Judge.model.entity.User;
import com.example.Judge.model.view.UserProfileViewModel;
import com.example.Judge.security.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService, CurrentUser currentUser, ModelMapper modelMapper){
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.currentUser = currentUser;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel){
        User user = modelMapper.map (userServiceModel, User.class);
        if(userRepository.count ()== 0){
            user.setRole (roleService.findRole (RoleNameEnum.ADMIN));
        }else{
            user.setRole (roleService.findRole (RoleNameEnum.USER));
        }
        userRepository.save (user);
    }

    @Override
    public UserServiceModel findUserByUsernameAndPassword(String username, String password){
        return userRepository.findByUsernameAndPassword (username,password)
                .map (user -> modelMapper.map (user, UserServiceModel.class))
                .orElse (null);
    }

    @Override
    public void login(UserServiceModel user){
        currentUser
                .setId (user.getId ())
                .setUsername (user.getUsername ())
                .setRole (user.getRole ().getName ());

    }

    @Override
    public void logout(){
        currentUser
                .setId (null)
                .setUsername (null)
                .setRole (null);
    }

    @Override
    public List<String> findAllUsernames(){
        return userRepository.findAllUsernames();
    }

    @Override
    public void changeRole(String username, RoleNameEnum roleNameEnum){
        User user = userRepository
                .findByUsername (username)
                .orElse (null);



            if(user.getRole ().getName () != roleNameEnum){
                user.setRole (roleService.findRole (roleNameEnum));
                userRepository.save (user);
        }


    }

    @Override
    public User findById(Long id){
        return userRepository.findById (id).orElse (null);
    }

    @Override
    public UserProfileViewModel findProfileById(Long id){

        User user = userRepository.findById (id).orElse (null);
        UserProfileViewModel userProfileViewModel = modelMapper
                .map (user,UserProfileViewModel.class)
                .setHomeworkSet (user
                        .getHomeworkSet ()
                        .stream ().map(homework -> homework.getExercise ()
                                .getName ())
                        .collect(Collectors.toSet()));
        return userProfileViewModel;
                
    }

    @Override
    public Long findUsersCount(){
        return userRepository.count ();
    }


}
