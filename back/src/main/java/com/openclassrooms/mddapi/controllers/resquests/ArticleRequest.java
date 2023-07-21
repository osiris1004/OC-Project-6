package com.openclassrooms.mddapi.controllers.resquests;


import com.openclassrooms.mddapi.modles.Theme;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleRequest {
    String title;
    String content;
    List<Integer> articleThemes;
    String authorName ;

}
