package com.openclassrooms.mddapi.controllers;

import java.util.List;

import com.openclassrooms.mddapi.modles.Article;
import com.openclassrooms.mddapi.services.Article.ArticleService;
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

    private  final ArticleService articleService;

    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> Comments() {
        return ResponseEntity.ok(commentService.getComments());
    }

    @GetMapping("/comment/{id}")
    public ResponseEntity<Comment> getComment(@PathVariable Integer id) {
        return ResponseEntity.ok(commentService.getCommentById(id));
    }

    @PostMapping("/comment")
    public ResponseEntity<Comment> saveComment(@RequestBody Comment commentRequest) {
        return ResponseEntity.ok(commentService.saveComment(commentRequest));
    }

    @PutMapping("/comment/{commentId}")
    public ResponseEntity<Comment> updateComment( @PathVariable Integer commentId, @RequestBody Comment commentRequest) {
        commentRequest.setId(commentId);
        return ResponseEntity.ok(commentService.updateComment(commentRequest));
    }


    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity deleteComment( @PathVariable Integer commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/article/{articleId}/comment")
    public ResponseEntity<Comment> saveCommentInArticle(@PathVariable Integer articleId, @RequestBody Comment commentRequest) {
        Article article = articleService.getArticleById(articleId);
        article.getComment().add(commentRequest);
        return ResponseEntity.ok(commentService.saveComment(commentRequest));
    }

}
