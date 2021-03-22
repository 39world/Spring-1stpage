package com.sparta.mystory.models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface StoryRespository extends JpaRepository<Story, Long> {
    List<Story> findAllByModifiedAtDesc(LocalDateTime ModifiedAt)
}
