package com.openclassrooms.mddapi.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.openclassrooms.mddapi.modles.Article;

import com.openclassrooms.mddapi.services.Article.ArticleService;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService ArticleService;
  

    @GetMapping("/article")
    public ResponseEntity<List<Article>> Articles() {
        return ResponseEntity.ok(ArticleService.getArticles());
    }

    @GetMapping("/article/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable Integer id) {
        return ResponseEntity.ok(ArticleService.getArticleById(id));
    }

    @PostMapping("/users/{userId}/article")
    public ResponseEntity<Article> saveArticle(@PathVariable Integer userId, @RequestBody Article ArticleRequest) {

    
        return ResponseEntity.ok(ArticleService.saveArticle(ArticleRequest));

    }

    @PutMapping("/article/{ArticleId}")
    public ResponseEntity<Article> updateArticle( @PathVariable Integer ArticleId, @RequestBody Article Article) {
        Article.setId(ArticleId);
        return ResponseEntity.ok(ArticleService.updateArticle(Article));
    }


    @DeleteMapping("/users/{userId}/article/{ArticleId}")
    public ResponseEntity deleteArticle(@PathVariable Integer userId, @PathVariable Integer ArticleId, @RequestBody Article ArticleRequest) {
       
        ArticleService.deleteArticle(ArticleId);
        return ResponseEntity.noContent().build();
    }

}
