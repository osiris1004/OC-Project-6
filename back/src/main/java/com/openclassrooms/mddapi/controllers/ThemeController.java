package com.openclassrooms.mddapi.controllers;

import java.util.List;

import com.openclassrooms.mddapi.modles.Article;
import com.openclassrooms.mddapi.services.Article.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.openclassrooms.mddapi.modles.Theme;
import com.openclassrooms.mddapi.modles.User.User;
import com.openclassrooms.mddapi.services.Theme.ThemeService;
import com.openclassrooms.mddapi.services.User.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class ThemeController {

    private final ThemeService themeService;
    private final UserService userService;

    private  final ArticleService articleService;

    @GetMapping("/themes")
    public ResponseEntity<List<Theme>> themes() {
        return ResponseEntity.ok(themeService.getThemes());
    }

    @GetMapping("/theme/{themeId}")
    public ResponseEntity<Theme> getTheme(@PathVariable Integer themeId) {
        return ResponseEntity.ok(themeService.getThemeById(themeId));
    }
    @PostMapping("/theme")
    public ResponseEntity<Theme> saveTheme(@RequestBody Theme themeRequest) {
        return ResponseEntity.ok(themeService.saveTheme(themeRequest));
    }
    @PutMapping("/theme/{themeId}")
    public ResponseEntity<Theme> updateTheme( @PathVariable Integer themeId, @RequestBody Theme theme) {
        theme.setId(themeId);
        return ResponseEntity.ok(themeService.updateTheme(theme));
    }
    @DeleteMapping("theme/{themeId}")
    public ResponseEntity deleteTheme(@PathVariable Integer themeId) {
        themeService.deleteTheme(themeId);
        return ResponseEntity.noContent().build();
    }


    // Add an exiting theme in to the user list
    @PostMapping("/user/{userId}/theme")
    public ResponseEntity<Theme> saveThemeToUser(@PathVariable Integer userId, @RequestBody Theme themeRequest) {
            User user = userService.getUserById(userId);
            Integer themeId = themeRequest.getId();
            Theme theme = themeService.getThemeById(themeId);
            user.addTheme(theme);
            return ResponseEntity.ok(themeService.saveTheme(theme));
    }

    // Add an exiting theme in to the article list
    @PostMapping("/article/{articleId}/theme")
    public ResponseEntity<Theme> saveThemeToArticle(@PathVariable Integer articleId, @RequestBody Theme themeRequest) {
        Article article = articleService.getArticleById(articleId);
        Integer themeId = themeRequest.getId();
        Theme theme = themeService.getThemeById(themeId);
        article.addTheme(theme);
        return ResponseEntity.ok(themeService.saveTheme(theme));
    }

    // Delete an exiting theme in to the user list

    @DeleteMapping("/user/{userId}/theme/{themeId}")
    public ResponseEntity deleteThemeFromUser(@PathVariable Integer userId, @PathVariable Integer themeId) {
        User user = userService.getUserById(userId);
        user.removeTheme(themeId);
        userService.saveUser(user);
        return ResponseEntity.noContent().build();
    }

    // Delete an exiting theme in to the Article list

    @DeleteMapping("/article/{articleId}/theme/{themeId}")
    public ResponseEntity deleteThemeFromArticle(@PathVariable Integer articleId, @PathVariable Integer themeId) {
        Article article = articleService.getArticleById(articleId);
        article.removeTheme(themeId);
        articleService.saveArticle(article);
        return ResponseEntity.noContent().build();
    }






}
