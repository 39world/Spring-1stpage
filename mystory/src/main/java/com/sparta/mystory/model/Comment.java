package com.sparta.mystory.model;



import com.sparta.mystory.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Comment extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;


    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Long storyId;


    public Comment(CommentRequestDto requestDto) {
        this.storyId = requestDto.getStoryId();
        this.writer = requestDto.getWriter();
        this.content = requestDto.getContent();
    }

    public void commentUpdate(CommentRequestDto commentDto) {
        this.content = commentDto.getContent();

    }
}
