package com.example.miniproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Article {
    @Id //대푯값
    @GeneratedValue
    private Long id;

    @Column
    private  String title; //DB의 title 열과 연결
    @Column
    private String content;

    public Article(Long id, String title, String content)
    {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString()
    {
        return "Article: title = " + title + ", content  = "+ content + id;
    }

}
