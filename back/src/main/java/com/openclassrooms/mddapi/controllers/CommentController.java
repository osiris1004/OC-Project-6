package com.openclassrooms.mddapi.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.openclassrooms.mddapi.modles.Comment;
import com.openclassrooms.mddapi.modles.User.User;
import com.openclassrooms.mddapi.services.Comment.CommentService;
import com.openclassrooms.mddapi.services.User.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/Comment")
    public ResponseEntity<List<Comment>> Comments() {
        return ResponseEntity.ok(commentService.getComments());
    }

    @GetMapping("/Comment/{id}")
    public ResponseEntity<Comment> getComment(@PathVariable Integer id) {
        return ResponseEntity.ok(commentService.getCommentById(id));
    }

    @PostMapping("/users/{userId}/Comment")
    public ResponseEntity<Comment> saveComment(@PathVariable Integer userId, @RequestBody Comment commentRequest) {
        return ResponseEntity.ok(commentService.saveComment(commentRequest));

    }

    @PutMapping("/Comment/{CommentId}")
    public ResponseEntity<Comment> updateComment( @PathVariable Integer CommentId, @RequestBody Comment commentRequest) {
        commentRequest.setId(CommentId);
        return ResponseEntity.ok(commentService.updateComment(commentRequest));
    }


    @DeleteMapping("/users/{userId}/Comment/{CommentId}")
    public ResponseEntity deleteComment(@PathVariable Integer userId, @PathVariable Integer CommentId, @RequestBody Comment commentRequest) {
       
        commentService.deleteComment(CommentId);
        return ResponseEntity.noContent().build();
    }

}
