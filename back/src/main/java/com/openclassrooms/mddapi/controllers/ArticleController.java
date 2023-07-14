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
  

    @GetMapping("/articles")
    public ResponseEntity<List<Article>> Articles() {
        return ResponseEntity.ok(ArticleService.getArticles());
    }

    @GetMapping("/article/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable Integer id) {
        return ResponseEntity.ok(ArticleService.getArticleById(id));
    }

    @PostMapping("article")
    public ResponseEntity<Article> saveArticle( @RequestBody Article ArticleRequest) {
        return ResponseEntity.ok(ArticleService.saveArticle(ArticleRequest));
    }

    @PutMapping("/article/{articleId}")
    public ResponseEntity<Article> updateArticle( @PathVariable Integer articleId, @RequestBody Article articleRequest) {
        articleRequest.setId(articleId);
        return ResponseEntity.ok(ArticleService.updateArticle(articleRequest));
    }


    @DeleteMapping("/article/{articleId}")
    public ResponseEntity deleteArticle( @PathVariable Integer articleId) {
        ArticleService.deleteArticle(articleId);
        return ResponseEntity.noContent().build();
    }

}
