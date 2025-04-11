package com.example.socialmedia.dto;

import lombok.Data;

import java.util.Set;

@Data
public class PostDTO {
    private Long id;
    private String title;
    private String body;
    private String author; // Author username
    private Set<String> likes; // List of usernames who liked the post
}
