package com.dailylog.post.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // 프로젝트 전체에서 발생하는 에러를 여기서 해결
public class GlobalExceptionHandler {

    // IllegalArgumentException이 발생하면 500 대신 이 메서드가 실행됨
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        // 에러 메시지와 함께 404 Not Found 상태 코드를 프론트엔드에 전달
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}