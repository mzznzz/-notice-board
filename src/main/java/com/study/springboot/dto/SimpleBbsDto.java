package com.study.springboot.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class SimpleBbsDto {
    private int id;
    private String writer;
    private String title;
    private String content;
    private String writeTime;  // 추가된 필드

    // 생성자 등은 필요에 따라 추가

    public void setCreatedAt(String createdAt) {
        this.writeTime = createdAt;
    }

    public String getCreatedAt() {
        return writeTime;
    }
}
