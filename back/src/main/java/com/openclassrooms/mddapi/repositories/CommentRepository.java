package com.openclassrooms.mddapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openclassrooms.mddapi.modles.Comment;



public interface CommentRepository extends JpaRepository<Comment, Integer>{
    
}
