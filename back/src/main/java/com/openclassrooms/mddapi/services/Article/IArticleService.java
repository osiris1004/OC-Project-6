package com.openclassrooms.mddapi.services.Article;

import java.util.List;

import com.openclassrooms.mddapi.modles.Article;

public interface IArticleService {
    List<Article> getArticles();
    Article saveArticle(Article article);
    Article getArticleById(Integer id);
    Article updateArticle(Article article);
    void deleteArticle(Integer id);


}
