package com.openclassrooms.mddapi.services.Comment;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.modles.Comment;
import com.openclassrooms.mddapi.repositories.CommentRepository;


import lombok.*;


@RequiredArgsConstructor
@Service
public class CommentService implements ICommentService{

    private final CommentRepository commentRepository;

    @Override
    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    @Override
    public Comment getCommentById(Integer id) {
        Optional<Comment> Comment = commentRepository.findById(id);
        if(Comment.isPresent()){ return Comment.get();}
        throw  new RuntimeException("Comment is not found for the id "+id);
    }
    @Override
    public Comment saveComment(Comment Comment) {
        return commentRepository.save(Comment);
    }

    @Override
    public Comment updateComment(Comment Comment) {
        return commentRepository.save(Comment);
    }

    @Override
    public void deleteComment(Integer id) {commentRepository.deleteById(id);}

}
