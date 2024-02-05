package com.example.miniproject.dto;

import com.example.miniproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor //생성자 자동 생성 어노테이션
@ToString //toString() 어노테이션
public class ArticleForm {
    private  Long id;
    private String title; //제목을 받을 필드
    private String content; //내용을 받을 필드


    public Article toEntity() {
        return new Article(id,title,content);
    }
}
