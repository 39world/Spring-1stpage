package com.sparta.mystory.repository;

import com.sparta.mystory.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByStoryId(Long Id);

}
