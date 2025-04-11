package com.example.socialmedia.service;

import com.example.socialmedia.dto.PostDTO;
import com.example.socialmedia.entity.Post;
import com.example.socialmedia.entity.User;
import com.example.socialmedia.mapper.PostMapper;
import com.example.socialmedia.repository.PostRepository;
import com.example.socialmedia.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostMapper postMapper;

    public PostService(PostRepository postRepository, UserRepository userRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.postMapper = postMapper;
    }

    public List<PostDTO> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PostDTO createPost(PostDTO postDTO, Long authorId) {
        User author = userRepository.findById(authorId).orElseThrow(() -> new RuntimeException("Author not found"));
        Post post = postMapper.toEntity(postDTO);
        post.setAuthor(author);
        post = postRepository.save(post);
        return postMapper.toDTO(post);
    }

    public PostDTO likePost(Long postId, Long userId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        post.getLikes().add(user);
        postRepository.save(post);

        return postMapper.toDTO(post);
    }
}
