package com.openclassrooms.mddapi.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.openclassrooms.mddapi.controllers.resquests.ArticleRequest;
import com.openclassrooms.mddapi.modles.Theme;
import com.openclassrooms.mddapi.services.Theme.ThemeService;
import com.openclassrooms.mddapi.services.User.UserService;
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
    private final ThemeService themeService;



    @GetMapping("/articles")
    public ResponseEntity<List<Article>> Articles() {
        return ResponseEntity.ok(ArticleService.getArticles());
    }

    @GetMapping("/article/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable Integer id) {
        return ResponseEntity.ok(ArticleService.getArticleById(id));
    }

    @PostMapping("article")
    public ResponseEntity<Article> saveArticle(@RequestBody ArticleRequest articleRequest) {
        List<Theme> themeList =  articleRequest.getArticleThemes().stream().map(
                (item) -> themeService.getThemeById(item) ).collect(Collectors.toList());
        Article newArticle = new Article(articleRequest.getTitle(), articleRequest.getContent(), articleRequest.getAuthorName());

        themeList.forEach((item)->newArticle.addTheme(item));

        return ResponseEntity.ok(ArticleService.saveArticle(newArticle));
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
