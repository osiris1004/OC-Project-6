package com.openclassrooms.mddapi.services.Comment;

import java.util.List;

import com.openclassrooms.mddapi.modles.Comment;

public interface ICommentService {
    List<Comment> getComments();
    Comment saveComment(Comment comment);
    Comment getCommentById(Integer id);
    Comment updateComment(Comment comment);
    void deleteComment(Integer id);


}
