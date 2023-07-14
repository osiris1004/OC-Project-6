package com.openclassrooms.mddapi.services.Article;




import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.modles.Article;
import com.openclassrooms.mddapi.repositories.ArticleRepository;


import lombok.*;


@RequiredArgsConstructor
@Service
public class ArticleService implements IArticleService{

    private final ArticleRepository articleRepository;

    @Override
    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    @Override
    public Article getArticleById(Integer id) {
        Optional<Article> Article = articleRepository.findById(id);
        if(Article.isPresent()){ return Article.get();}
        throw  new RuntimeException("Article is not found for the id "+id);
    }
    @Override
    public Article saveArticle(Article Article) {
        return articleRepository.save(Article);
    }

    @Override
    public Article updateArticle(Article Article) {
        return articleRepository.save(Article);
    }

    @Override
    public void deleteArticle(Integer id) {articleRepository.deleteById(id);}

}
