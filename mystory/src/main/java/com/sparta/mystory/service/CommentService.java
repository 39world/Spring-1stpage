package com.sparta.mystory.service;

import com.sparta.mystory.dto.CommentRequestDto;
import com.sparta.mystory.model.Comment;
import com.sparta.mystory.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@RequiredArgsConstructor
@Service
public class CommentService {


    private final CommentRepository commentRepository;

    @Transactional
    public Long commentUpdate(Long id, CommentRequestDto requestDto){

        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다.")
        );
        comment.commentUpdate(requestDto);
        return comment.getId();
    }
}
