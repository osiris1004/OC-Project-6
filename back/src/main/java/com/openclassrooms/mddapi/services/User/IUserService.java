package com.openclassrooms.mddapi.services.User;

import com.openclassrooms.mddapi.modles.User.User;

public interface IUserService {
    User getUserByEmail(String email);
    User getUserById(Integer id);
}
