package com.sparta.mystory.repository;

import com.sparta.mystory.model.Story;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoryRepository extends JpaRepository<Story, Long> {
    List<Story> findAllByOrderByModifiedAtDesc();

    List<Story> findAllByuserId(Long userId);


}