package com.openclassrooms.mddapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openclassrooms.mddapi.modles.Article;



public interface ArticleRepository extends JpaRepository<Article, Integer>{
    
}
