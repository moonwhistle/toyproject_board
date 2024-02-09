package com.example.miniproject.api;

import com.example.miniproject.dto.ArticleForm;
import com.example.miniproject.entity.Article;
import com.example.miniproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
public class ArticleApiController {
    @Autowired
    private ArticleRepository articleRepository;
    //GET
    @GetMapping("/api/articles")
    public List<Article> index() //전체 목록
    {
        return articleRepository.findAll();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id)
    {
        return articleRepository.findById(id).orElse(null);
    }


    //POST
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto) //@RequestBody 안써주면 데이터가 안실림
    {
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }

    //PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto)
    {
        //1. DTO -> 엔티티 변환
        Article article = dto.toEntity();
        log.info("id: {}, article: {}",id,article.toString());
        //2. 타깃 조회
        Article target = articleRepository.findById(id).orElse(null);
        //3. 잘못된 요청 처리
        if(target == null || !id.equals(article.getId())) //잘못된 요청인지 판별
        {
            log.info("잘못된 요청! id: {}, article: {}",id,article);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        //4. 업데이트 및 응담
        target.patch(article);
        Article updated = articleRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    //DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id)
    {
        //1. 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
        //2. 잘못된 요청 처리
        if(target == null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        //3. 삭제
        articleRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
