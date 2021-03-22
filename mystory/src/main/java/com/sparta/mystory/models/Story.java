package com.sparta.mystory.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Story extends Timestamped{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long num;

    private String title;

    private String writer;

    private String content;












}
