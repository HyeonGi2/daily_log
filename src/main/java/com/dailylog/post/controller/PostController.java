/**
* 타임리프 방식이 아닌 실무표준으로 변경 -> json, REST API + React/Vue
* @Controller (HTMl 파일명 반환) -> @RestController (데이터 반환)
* */

package com.dailylog.post.controller;

import com.dailylog.post.domain.Post;
import com.dailylog.post.dto.PostCreateRequest;
import com.dailylog.post.dto.PostResponse;
import com.dailylog.post.dto.PostUpdateRequest;
import com.dailylog.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/*
@RequiredArgsConstructor
@Controller //
public class PostController {
    private final PostService postService; // 비즈니스 로직 담당

    // 메인 화면 (게시글 목록)
    @GetMapping("/") //사용자가 도메인 주소 입력하면 실행
    public String list(Model model) {
        List<Post> posts = postService.findPosts(); // db에서 게시글 가져오기
        model.addAttribute("posts", posts); //화면에 데이터를 넘겨주기 위해 담기
        return "post/list"; //list.html 화면 출력

    }

    // 게시글 작성 화면
    @GetMapping("/post/new") // 글 작성 버튼 클릭 시, /post/new로 이동
    public String createForm() {
        return "post/createForm"; // 글 작성 html 출력
    }

    // 작성한 내용 db 저장
    @PostMapping("/post/new")
    public String create(@RequestParam String title, @RequestParam String content) {
        postService.write(title, content); // 입력받은 제목과 내용 서비스로 이동시켜 DB에 저장
        return "redirect:/"; // 저장 완료 후, 메인화면으로 이동
    } */


@RestController // 중요. HTML 파일명이 아닌, 데이터(JSON) 반환.
@RequiredArgsConstructor
@RequestMapping("/api/posts") // 이 컨트롤러의 모든 주소는 /api/posts로 시작
public class PostController {

    private final PostService postService;

    /**
    모든 게시글 목록 조회
    접근 주소: GET 8081/api/posts
    */
    @GetMapping
    public List<Post> getPosts() {
        // model 사용 x, db에서 찾은 리스트를 바로 출력
        // 스프링부트 내에서 데이터를 json 형태로 변환, 프론트엔드 전달
        return postService.findPosts();
    }

    /**
    * 접근 주소: POST 8081/api/posts
    * */
    @PostMapping
    public Long createPost(@RequestBody PostCreateRequest request) {
        // @RequestBody: 프론트엔드가 보내온 json을 dto 객체와 맵핑
        return postService.write(request.getTitle(), request.getContent());
    }

    /**
     * 특정 게시글 하나 조회
     * 접근 주소: GET 8081/api/posts/{id}
     */
    @GetMapping("/{id}")
    public PostResponse getPost(@PathVariable Long id) {
        // @PathVariable: 주소에 있는 id 값을 Long id에 넣어줌
        return postService.findById(id);
    }

    /**
     * 특정 게시글 수정
     * 접근 주소: PUT 8081/api/posts/{id}
     */
    @PutMapping("/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostUpdateRequest request) {
        return postService.update(id, request.getTitle(), request.getContent());
    }

    /**
     * 특정 게시글 삭제
     * 접근 주소: DELETE 8081/api/posts/{id}
     */
    @DeleteMapping("/{id}")
    public Long deletePost(@PathVariable Long id) {
        postService.delete(id);
        return id;
    }
}
