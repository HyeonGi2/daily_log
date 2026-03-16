package com.dailylog.post.service;

import com.dailylog.post.domain.Post;
import com.dailylog.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor //final, @NotNull 필드의 생성자 자동 생성(의존성 주입)
@Service // 비즈니스 로직 담당
public class PostService {
    // 데이터를 db에 저장 및 사용을 위해 repository를 가져옴
    private final PostRepository postRepository;

    @Transactional // 이 메서드가 실행될 때 트랙잭션 시작, 정상 종료되면 커밋, 에러 시 롤백
    public Long write(String title, String content) {
        // 엔티티 객체 생성 ( builder 패턴)
        Post post = Post.builder()
                .title(title)
                .content(content)
                .build();

        // 리포지터리를 이용해 db에 저장, 저장된 게시글 Id 반환
        return postRepository.save(post).getId();

    }

    //전체 게시글 조회
    @Transactional(readOnly = true) //
    public List<Post> findPosts() {
        return postRepository.findAll();
    }

}
