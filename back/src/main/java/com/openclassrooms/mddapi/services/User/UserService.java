package com.openclassrooms.mddapi.services.User;


import java.util.List;
import java.util.Optional;

import com.openclassrooms.mddapi.modles.Theme;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.modles.User.User;
import com.openclassrooms.mddapi.repositories.UserRepository;

import lombok.*;


@RequiredArgsConstructor
@Service
public class UserService implements IUserService{

    private final UserRepository userRepository;

    @Override
    public List<User> getUsers() { return userRepository.findAll();}
    @Override
    public User getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            return user.get();
        }  throw  new RuntimeException(email+ "does not exist");
    }

    @Override
    public User getUserById(Integer id) {
        Optional<User> rental = userRepository.findById(id);
        if(rental.isPresent()){
            return rental.get();
        }
        throw  new RuntimeException("Not found rentals with id = " + id);
    }
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

}
