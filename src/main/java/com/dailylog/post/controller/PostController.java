package com.dailylog.post.controller;

import com.dailylog.post.domain.Post;
import com.dailylog.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller //
public class PostController {
    private final PostService postService; // 비즈니스 로직 담당

    //메인 화면 (게시글 목록)
    @GetMapping("/") //사용자가 도메인 주소 입력하면 실행
    public String list(Model model) {
        List<Post> posts = postService.findPosts(); // db에서 게시글 가져오기
        model.addAttribute("posts", posts); //화면에 데이터를 넘겨주기 위해 담기
        return "post/list"; //list.html 화면 출력

    }
}
