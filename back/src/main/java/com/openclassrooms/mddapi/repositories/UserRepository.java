package com.openclassrooms.mddapi.repositories;


import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.openclassrooms.mddapi.modles.User.User;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByEmail(String email);
}