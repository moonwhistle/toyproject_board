package com.example.miniproject.entity;

import com.example.miniproject.dto.CommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동으로 1씩 증가
    private Long id; //대표키

    @ManyToOne //Comment 엔티티와 Article엔티티를 다대일 관계로 설정
    @JoinColumn(name = "article_id") //외래키 생성, Article 엔티티의 기본키(id)와 매핑
    private  Article article;//해당 댓글의 부모 게시글

    @Column
    private String nickname; //댓글 단 사람

    @Column
    private String body; //댓글 본문

    public static Comment createComment(CommentDto dto, Article article) {
        //예외 발생
        if(dto.getId() != null) //댓글 생성 이전에 id가 있을 수 없음
        {
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다.");
        }
        if(dto.getArticleId() != article.getId())
        {
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못됐습니다.");
        }
        //엔티티 생성 및 반환
        return new Comment(
                dto.getId(),//댓글 아이디
                article, //부모 게시글
                dto.getNickname(), //댓글 닉네임
                dto.getBody() //댓글 본문
        );
    }
}
