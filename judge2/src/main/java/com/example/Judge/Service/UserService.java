package com.example.Judge.Service;
import java.util.*;
import com.example.Judge.model.Service.UserServiceModel;
import com.example.Judge.model.entity.RoleNameEnum;
import com.example.Judge.model.entity.User;
import com.example.Judge.model.view.UserProfileViewModel;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findUserByUsernameAndPassword(String username, String password);

    void login(UserServiceModel user);

    void logout();

    List<String> findAllUsernames();

    void changeRole(String username, RoleNameEnum roleNameEnum);

    User findById(Long id);

    UserProfileViewModel findProfileById(Long id);

    Long findUsersCount();
}
