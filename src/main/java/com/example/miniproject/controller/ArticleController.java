package com.example.miniproject.controller;

import com.example.miniproject.dto.ArticleForm;
import com.example.miniproject.dto.CommentDto;
import com.example.miniproject.entity.Article;
import com.example.miniproject.repository.ArticleRepository;
import com.example.miniproject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;


@Controller
@Slf4j //로깅 어노테이션 추가
public class ArticleController {

    @Autowired //레파지토리 객체 주입
    private ArticleRepository articleRepository;
    @Autowired
    private CommentService commentService;
    @GetMapping("/articles/new")
    public String newArticleForm()
    {
        return "articles/new";
    }

    @PostMapping("/articles/create") //URL 요청 접수
    public String createArticle(ArticleForm form) //퓸 데이터를 DTO로 받기
    {
        //1 DTO를 엔티티로 변환
        Article article = form.toEntity();
        log.info(form.toString());
        log.info(article.toString());
        //2 레파지토리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);
        log.info(saved.toString());

        return "redirect:/articles/"+ saved.getId(); //상세페이지 URL
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model)
    {
        log.info("id = "+id); //id 잘 받았는지 확인

        //1. id 조회해 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        List<CommentDto> commentDtos = commentService.comments(id);
        //2. 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);
        model.addAttribute("commentDtos",commentDtos); //댓글 목록 모델에 등록
        //3 뷰 페이지 반환
        return "articles/show";
    }
    @GetMapping("/articles")
    public String index(Model model)
    {
        //1. 모든 데이터 가져오기
        ArrayList<Article> articleEntityList =  articleRepository.findAll();//
        //2.모델에 데이터 등록
        model.addAttribute("articleList", articleEntityList);
        //3. 뷰 페이지 설정
        return "articles/index"; //목록 View
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) //id를 매개변수로 가져오기
    {
        //수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        model.addAttribute("article", articleEntity);

        return "articles/edit";
    }

    @PostMapping("articles/update")
    public String update(ArticleForm form)
    {
        log.info(form.toString());

        //1. dto -> entity
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());
        //2. entity DB저장
        //기존 값 가져오기
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        //기존 값 갱신
        if(target != null)
        {
            articleRepository.save(articleEntity);
        }

        //3. 수정 결과 페이지로 리다이렉트
        return "redirect:/articles/"+articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr)
    {
        log.info("삭제 요청이 들어왔습니다 ! ");

        //1. 삭제할 대상 가져오기
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());
        //2. 대상 엔티티 삭제하기
        if(target != null)
        {
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg","삭제됐습니다!"); //삭제 메세지
        }
        //3. 결과 페이지로 리다이렉트 하기
        return "redirect:/articles";
    }


  
}
