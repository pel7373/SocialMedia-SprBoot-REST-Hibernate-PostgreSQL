package com.example.socialmedia.service;

import com.example.socialmedia.dto.UserDTO;
import com.example.socialmedia.entity.User;
import com.example.socialmedia.mapper.UserMapper;
import com.example.socialmedia.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UserDTO followUser(Long userId, Long followerId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        User follower = userRepository.findById(followerId).orElseThrow(() -> new RuntimeException("Follower not found"));

        user.getFollowers().add(follower);
        userRepository.save(user);

        return userMapper.toDTO(user);
    }
}
