package com.example.miniproject.controller;

import com.example.miniproject.dto.ArticleForm;
import com.example.miniproject.entity.Article;
import com.example.miniproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@Slf4j //로깅 어노테이션 추가
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
        log.info(form.toString());
        log.info(form.toString());
        //2 레파지토리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);
        log.info(saved.toString());

        return "";
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model)
    {
        log.info("id = "+id); //id 잘 받았는지 확인

        //1. id 조회해 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        //2. 모델에 데잍처 등록하기
        model.addAttribute("article", articleEntity);
        //3 뷰 페이지 반환
        return "articles/show";
    }
}
