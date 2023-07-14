package com.openclassrooms.mddapi.services.Theme;

import java.util.List;

import com.openclassrooms.mddapi.modles.Theme;

public interface IThemeService {
    List<Theme> getThemes();
    Theme saveTheme(Theme theme);
    Theme getThemeById(Integer id);
    Theme updateTheme(Theme theme);

    void deleteTheme(Integer id);


}
