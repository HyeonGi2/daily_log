package com.dailylog.post.dto;

import lombok.Getter;


@Getter
public class PostUpdateRequest {
    // 수정 시 프론트엔드에서 받을 데이터
    private String title;
    private String content;
}