package com.example.miniproject.service;

import com.example.miniproject.dto.ArticleForm;
import com.example.miniproject.entity.Article;
import com.example.miniproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;
    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();

        if(article.getId() != null)
        {
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        //1. DTO -> 엔티티 변환
        Article article = dto.toEntity();
        log.info("id: {}, article: {}",id,article.toString());
        //2. 타깃 조회
        Article target = articleRepository.findById(id).orElse(null);
        //3. 잘못된 요청 처리
        if(target == null || !id.equals(article.getId())) //잘못된 요청인지 판별
        {
            log.info("잘못된 요청! id: {}, article: {}",id,article);
            return null;
        }
        //4. 업데이트 및 응담
        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated; //수정한 대상을 컨트롤러에 반환

    }

    public Article delete(Long id) {
        //1. 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
        //2. 잘못된 요청 처리
        if(target == null)
        {
            return null;
        }
        //3. 삭제
        articleRepository.delete(target);
        return target; //삭제한 대상을 컨트롤러에 반환

    }
    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {
        //1. dto 묶음을 엔티티 묶음으로 변환
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());
        //2. 엔티티 묶음을 DB에 저장
        articleList.stream().forEach(article -> articleRepository.save(article));
        //3. 강제 예외 발생
        articleRepository.findById(-1L) //의도적으로 맞지 않은 데이터 추가
                .orElseThrow(()->new IllegalArgumentException("결재 실패!")); //맞는 데이터가 없으면 예외 발생
        //4. 결과 값 반환
        return articleList;
    }
}
