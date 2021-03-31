package com.sparta.mystory.model;

import com.sparta.mystory.dto.StoryRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity
public class Story extends Timestamped{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Long userId;


    public Story(StoryRequestDto requestDto,Long userId){
        this.userId = userId;
        this.title = requestDto.getTitle();
        this.writer = requestDto.getWriter();
        this.content = requestDto.getContent();
    }

    public void update(StoryRequestDto storyDto){
        this.content = storyDto.getContent();
    }











}
