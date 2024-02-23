package com.example.miniproject.service;

import com.example.miniproject.dto.CommentDto;
import com.example.miniproject.entity.Comment;
import com.example.miniproject.repository.ArticleRepository;
import com.example.miniproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId)
    {
        /*//1. 댓글 조회
        List<Comment> comments = commentRepository.findByArticleId(articleId);
        //2. 엔티티 -> DTO 변환
        List<CommentDto> dtos = new ArrayList<CommentDto>();
        for(int i=0 ;i<comments.size();i++)
        {
            Comment c = comments.get(i);
            CommentDto dto = CommentDto.createCommentDto(c);
            dtos.add(dto);
        }
        //3. 결과 반환
        return dtos;*/

        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment)) //.map(a->b) : 스트림의 각 요소(a)를 꺼내 b를 수행한 결과로 매핑
                .collect(Collectors.toList()); //스트림을 리스트로 변환
    }
}
