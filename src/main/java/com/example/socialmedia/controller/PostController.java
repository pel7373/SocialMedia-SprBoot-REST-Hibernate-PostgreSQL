package com.example.socialmedia.controller;

import com.example.socialmedia.dto.PostDTO;
import com.example.socialmedia.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @PostMapping("/{authorId}")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO, @PathVariable Long authorId) {
        return ResponseEntity.ok(postService.createPost(postDTO, authorId));
    }

    @PostMapping("/{postId}/like/{userId}")
    public ResponseEntity<PostDTO> likePost(@PathVariable Long postId, @PathVariable Long userId) {
        return ResponseEntity.ok(postService.likePost(postId, userId));
    }
}
