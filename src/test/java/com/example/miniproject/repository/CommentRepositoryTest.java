package com.example.miniproject.repository;

import com.example.miniproject.api.ArticleApiController;
import com.example.miniproject.entity.Article;
import com.example.miniproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest //@SpringBootTest 어노테이션은 스프링 부트와 연동한 테스트, 레파지토리는 JPA와 연동하여 테스트함
class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;
    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회") //메서드명 바꾸지 않고 이 어노테이션 통해서 바꿔 보여줌
    void findByArticleId() {
        // case 1: 4번 게시글의 모든 댓글 조회 //

        //1. 입력 데이터 준비
        Long articleId = 4L;
        //2. 실제 데이터
        List<Comment> comments = commentRepository.findByArticleId(articleId);
        //3. 예상 데이터
        Article article = new Article(4L, "당신의 인생 영화","댓글 고");
        Comment a = new Comment(1L, article, "park","굿 윌 헌팅");
        Comment b = new Comment(2L, article, "kim","아이언맨");
        Comment c = new Comment(3L, article, "choi","스파이더맨");

        List<Comment> expected = Arrays.asList(a,b,c);
        //4. 비교 및 검증
        assertEquals(expected.toString(), comments.toString(),"4번 글의 모든 댓글을 출력!");

        // case 2: 1번 게시글의 모든 댓글 조회 //

        //1. 입력 데이터 준비
        Long articleId1 = 1L;
        //2. 실제 데이터
        List<Comment> comments1 = commentRepository.findByArticleId(articleId1);
        //3. 예상 데이터
        Article article1 = new Article(1L,"가가가","111");
        List<Comment> expected1 = Arrays.asList();
        //4. 비교 및 검증
        assertEquals(expected1.toString(),comments1.toString(),"1번 글은 댓글이 없음");
    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        // case 1: "park"의 모든 댓글 조회
        //1. 입력 데이터 준비
        String name1 = "park";
        //2. 실제 데이터
        List<Comment> comments1 = commentRepository.findByNickname(name1);
        //3. 예상 데이터
        Comment a = new Comment(1L, new Article(4L,"당신의 인생 영화","댓글 고"),name1,"굿 윌 헌팅");
        Comment b = new Comment(4L, new Article(5L,"당신의 소울푸드","댓글 고고"),name1,"치킨");
        Comment c = new Comment(7L, new Article(6L,"당신의 취미","댓글 고고고"),name1,"조깅");

        List<Comment> expected1 = Arrays.asList(a,b,c);
        //4. 비교 및 검증
        assertEquals(expected1.toString(),comments1.toString(),"park의 모든 댓글을 출력");
    }
}