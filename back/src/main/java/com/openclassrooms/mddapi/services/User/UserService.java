package com.openclassrooms.mddapi.services.User;


import java.util.Optional;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.modles.User.User;
import com.openclassrooms.mddapi.repositories.UserRepository;

import lombok.*;


@RequiredArgsConstructor
@Service
public class UserService implements IUserService{

    private final UserRepository userRepository;

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
        throw  new ResourceNotFoundException("Not found rentals with id = " + id);
    }

}
 class ResourceNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public ResourceNotFoundException(String msg) {
      super(msg);
    }

}