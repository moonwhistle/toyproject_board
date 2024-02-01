package com.example.miniproject.controller;

import com.example.miniproject.dto.ArticleForm;
import com.example.miniproject.entity.Article;
import com.example.miniproject.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    @Autowired //레파지토리 객체 주입
    private ArticleRepository articleRepository;
    @GetMapping("/articles/new")
    public String newArticleForm()
    {
        return "articles/new";
    }

    @PostMapping("/articles/create") //URL 요청 접수
    public String createArticle(ArticleForm form) //퓸 데이터를 DTO로 받기
    {
        //1 DTO를 엔티티로 변환
        Article article = form.toEntity();
        System.out.println(article.toString()); //변환 확인
        //2 레파지토리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);
        System.out.println(saved.toString());

        return "";
    }
}
