package com.example.miniproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@ToString
@NoArgsConstructor //기본 생성자 어노테이션
public class Article {
    @Id //대푯값
    @GeneratedValue
    private Long id;

    @Column
    private  String title; //DB의 title 열과 연결
    @Column
    private String content;



}
