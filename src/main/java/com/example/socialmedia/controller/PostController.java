package com.example.socialmedia.controller;

import com.example.socialmedia.dto.PostDTO;
import com.example.socialmedia.dto.UserDTO;
import com.example.socialmedia.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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

    @PostMapping("/{postId}/like/{userId}")
    public ResponseEntity<PostDTO> likePost(@PathVariable Long postId, @PathVariable Long userId) {
        return ResponseEntity.ok(postService.likePost(postId, userId));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    @GetMapping("/{postId}/likes")
    public ResponseEntity<Set<UserDTO>> getLikes(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.getLikes(postId));
    }

    @PostMapping("/{authorId}")
    public ResponseEntity<PostDTO> createPost(@RequestBody @Valid PostDTO postDTO, @PathVariable Long authorId) {
        return ResponseEntity.ok(postService.createPost(postDTO, authorId));
    }
}
