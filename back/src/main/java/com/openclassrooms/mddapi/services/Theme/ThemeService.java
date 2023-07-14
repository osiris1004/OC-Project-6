package com.openclassrooms.mddapi.services.Theme;




import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.modles.Theme;
import com.openclassrooms.mddapi.repositories.ThemeRepository;


import lombok.*;


@RequiredArgsConstructor
@Service
public class ThemeService implements IThemeService{

    private final ThemeRepository themeRepository;

    @Override
    public List<Theme> getThemes() {
        return themeRepository.findAll();
    }

    @Override
    public Theme getThemeById(Integer id) {
        Optional<Theme> theme = themeRepository.findById(id);
        if(theme.isPresent()){ return theme.get();}
        throw  new RuntimeException("theme is not found for the id "+id);
    }
    @Override
    public Theme saveTheme(Theme theme) {
        return themeRepository.save(theme);
    }

    @Override
    public Theme updateTheme(Theme theme) {
        return themeRepository.save(theme);
    }

    @Override
    public void deleteTheme(Integer id) {themeRepository.deleteById(id);}

}
