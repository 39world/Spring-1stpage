package com.sparta.mystory.controller;


import com.sparta.mystory.models.Story;
import com.sparta.mystory.models.StoryRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class StoryRestController {



    private final StoryRepository storyRepository;
    private final StoryService  storyService;

    @PostMapping("/api/stoires")
    public Story createStory(@RequestBody StoryRequestDto storyRequestDto){
        Story story = new Story(storyRequestDto);
        return storyRepository.save(story);
    }







}
