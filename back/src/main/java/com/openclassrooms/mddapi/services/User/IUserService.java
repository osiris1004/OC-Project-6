package com.openclassrooms.mddapi.services.User;

import com.openclassrooms.mddapi.modles.Article;
import com.openclassrooms.mddapi.modles.Theme;
import com.openclassrooms.mddapi.modles.User.User;

import java.util.List;

public interface IUserService {

    List<User> getUsers();
    User getUserByEmail(String email);
    User getUserById(Integer id);
    User saveUser(User user);
    User updateUser(User user);
}
