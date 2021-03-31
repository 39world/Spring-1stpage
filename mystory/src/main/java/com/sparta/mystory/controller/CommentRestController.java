package com.sparta.mystory.controller;


import com.sparta.mystory.dto.CommentRequestDto;
import com.sparta.mystory.model.Comment;
import com.sparta.mystory.repository.CommentRepository;
import com.sparta.mystory.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentRestController {


    private final CommentRepository commentRepository;
    private final CommentService commentService;


    @PostMapping("/api/comment/")
    public Comment createComment(@RequestBody CommentRequestDto commentRequestDto) {
        System.out.println(commentRequestDto);
        Comment comment = new Comment(commentRequestDto);
        return commentRepository.save(comment);
    }

    @DeleteMapping("/api/comment/{id}")
    public Long deleteStory(@PathVariable Long id) {
        commentRepository.deleteById(id);
        return id;
    }

    @PutMapping("/api/comment/{id}")
    public Long update(@PathVariable Long id, @RequestBody CommentRequestDto requestDto) {
        commentService.commentUpdate(id, requestDto);
        return id;
    }


    @GetMapping("/api/comment/{id}")
    public List<Comment> createComment(@PathVariable Long id) {
        return commentRepository.findAllByStoryId(id);

    }
}

