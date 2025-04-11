package com.example.socialmedia.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

import java.util.Set;

@Data
public class PostDTO {
    private Long id;

    @NotBlank(message = "Title cannot be empty")
    private String title;

    @NotBlank(message = "Body cannot be empty")
    private String body;

    private String author; // Author username
    private Set<String> likes; // List of usernames who liked the post
}
