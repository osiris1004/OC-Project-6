package com.openclassrooms.mddapi.controllers;

import java.util.List;

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

    @GetMapping("/theme")
    public ResponseEntity<List<Theme>> themes() {
        return ResponseEntity.ok(themeService.getThemes());
    }

    @GetMapping("/theme/{id}")
    public ResponseEntity<Theme> getTheme(@PathVariable Integer id) {
        return ResponseEntity.ok(themeService.getThemeById(id));
    }

    @PostMapping("/users/{userId}/theme")
    public ResponseEntity<Theme> saveTheme(@PathVariable Integer userId, @RequestBody Theme themeRequest) {

        if(userId > 0){
            User user = userService.getUserById(userId);

            // Theme is existed
            Integer themeId = themeRequest.getId();
            if(themeId !=null && themeId != 0){
                Theme theme = themeService.getThemeById(themeId);
                user.addTheme(theme);
                return ResponseEntity.ok(themeService.saveTheme(theme));
            }

            // add and create new Theme
            user.addTheme(themeRequest);
            return ResponseEntity.ok(themeService.saveTheme(themeRequest));
        }
        return ResponseEntity.ok(themeService.saveTheme(themeRequest));

    }

    @PutMapping("/theme/{themeId}")
    public ResponseEntity<Theme> updateTheme( @PathVariable Integer themeId, @RequestBody Theme theme) {
        theme.setId(themeId);
        return ResponseEntity.ok(themeService.updateTheme(theme));
    }


    @DeleteMapping("/users/{userId}/theme/{themeId}")
    public ResponseEntity deleteTheme(@PathVariable Integer userId, @PathVariable Integer themeId, @RequestBody Theme themeRequest) {
        if(userId > 0){
            //delete theme from user
            User user = userService.getUserById(userId);
            user.removeTheme(themeId);
            userService.saveUser(user);
            return ResponseEntity.noContent().build();
        }

        //delete theme
        themeService.deleteTheme(themeId);
        return ResponseEntity.noContent().build();
    }




}
