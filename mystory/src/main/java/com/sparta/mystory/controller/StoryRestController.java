package com.sparta.mystory.controller;

import com.sparta.mystory.model.Story;
import com.sparta.mystory.repository.StoryRepository;
import com.sparta.mystory.dto.StoryRequestDto;
import com.sparta.mystory.security.UserDetailsImpl;
import com.sparta.mystory.service.StoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class StoryRestController {

    private final StoryRepository storyRepository;
    private final StoryService storyService;

    @PostMapping("/api/stories")
    public Story createStory(@RequestBody StoryRequestDto storyRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        Long userId = userDetails.getUser().getId();
        Story story = new Story(storyRequestDto, userId);

        return storyRepository.save(story);
    }

    @GetMapping("/api/stories")
    public List<Story> getStory(){
        return storyRepository.findAllByOrderByModifiedAtDesc();
    }

    @DeleteMapping("/api/stories/{id}")
    public Long deleteStory(@PathVariable Long id){
        storyRepository.deleteById(id);
        return id;
    }

    @PutMapping("/api/stories/{id}")
    public Long updateStory(@PathVariable Long id, @RequestBody StoryRequestDto requestDto){
        storyService.update(id,requestDto);
        return id;
    }

    @GetMapping("/api/pop/{id}")
    public Story createStory(@PathVariable Long id){
        Story story = storyRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다.")
        );
        return story;
    }

    @GetMapping("/api/writerstory")
    public List<Story> getStory(@AuthenticationPrincipal UserDetailsImpl userDetails){
        Long userId = userDetails.getUser().getId();
        return storyRepository.findAllByuserId(userId);
    }
}
