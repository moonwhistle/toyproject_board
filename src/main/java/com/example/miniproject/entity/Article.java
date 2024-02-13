package com.example.miniproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@ToString
@NoArgsConstructor //기본 생성자 어노테이션
@Getter
public class Article {
    @Id //대푯값
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private  String title; //DB의 title 열과 연결
    @Column
    private String content;

    public void patch(Article article) {
        if(article.title != null)
        {
            this.title = article.title;
        }
        if(article.content != null)
        {
            this.content = article.content;
        }
    }


}
