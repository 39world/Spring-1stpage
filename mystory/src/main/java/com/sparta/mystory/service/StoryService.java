package com.sparta.mystory.service;


import com.sparta.mystory.model.Story;
import com.sparta.mystory.repository.StoryRepository;
import com.sparta.mystory.dto.StoryRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class StoryService {

    private final StoryRepository storyRepository;

    @Transactional
    public Long update(Long id, StoryRequestDto requestDto){

        Story story = storyRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다.")
        );
        story.update(requestDto);
        return story.getId();
    }
}
