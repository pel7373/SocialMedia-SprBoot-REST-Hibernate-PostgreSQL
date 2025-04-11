package com.example.socialmedia.service;

import com.example.socialmedia.dto.UserDTO;
import com.example.socialmedia.entity.User;
import com.example.socialmedia.mapper.UserMapper;
import com.example.socialmedia.repository.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private final UserRepository userRepository = mock(UserRepository.class);
    private final UserMapper userMapper = mock(UserMapper.class);
    private final UserService userService = new UserService(userRepository, userMapper);

    @Test
    void getAllUsers() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");

        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));
        when(userMapper.toDTO(user)).thenReturn(new UserDTO());

        List<UserDTO> users = userService.getAllUsers();

        assertEquals(1, users.size());
        verify(userRepository, times(1)).findAll();
    }
}