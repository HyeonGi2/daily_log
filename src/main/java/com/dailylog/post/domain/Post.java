/*
* @id import 하기
* */
package com.dailylog.post.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Post {
    @Id //기본 키(Primary Key) 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 1부터 1씩 자동 증가
    private Long id;

    @Column(nullable = false, length = 500) // 글자수 최대 500, 공백 불가
    private String title; //

    @Column(nullable = false, columnDefinition = "TEXT") //
    private String content;

    @Builder // 객채 생성 시 값 편하게 넣기
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }
}