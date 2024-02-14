package com.example.miniproject.repository;

import com.example.miniproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    //특정 게시글의 모든 댓글 조회
    @Query(value = "SELECT * FROM comment WHERE article_id = :articleId", nativeQuery = true) //SQL에서 쓰는 쿼리를 이렇게 가져올 수도 있음
    List<Comment> findByArticleId(Long articleId);
    //특정 닉네임의 모든 댓글 조회
    List<Comment> findByNickname(String nickname);


}
