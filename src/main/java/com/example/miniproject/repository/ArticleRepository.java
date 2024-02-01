package com.example.miniproject.repository;

import com.example.miniproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository <Article, Long>{
}
