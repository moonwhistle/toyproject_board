package com.example.miniproject.dto;

import com.example.miniproject.entity.Article;

public class ArticleForm {
    private String title; //제목을 받을 필드
    private String content; //내용을 받을 필드

    public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Article toEntity() {
        return new Article(null,title,content);
    }
}
