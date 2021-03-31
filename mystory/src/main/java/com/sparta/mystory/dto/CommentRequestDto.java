package com.sparta.mystory.dto;


import lombok.Getter;

@Getter
public class CommentRequestDto {

    private Long storyId;
    private String writer;
    private String content;
}
