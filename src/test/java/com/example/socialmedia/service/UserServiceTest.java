package com.example.socialmedia.service;

import com.example.socialmedia.dto.UserDTO;
import com.example.socialmedia.entity.User;
import com.example.socialmedia.mapper.UserMapper;
import com.example.socialmedia.repository.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

    @Test
    void testGetFollowing() {
        // Create User A
        User userA = new User();
        userA.setId(1L);
        userA.setUsername("userA");

        // Create User B
        User userB = new User();
        userB.setId(2L);
        userB.setUsername("userB");

        // Create User C
        User userC = new User();
        userC.setId(3L);
        userC.setUsername("userC");

        // Use helper method to add following
        userA.addFollowing(userB);
        userA.addFollowing(userC);

        // Mock the repository to return User A
        when(userRepository.findById(1L)).thenReturn(Optional.of(userA));

        // Mock the mapper to return UserDTOs for userB and userC
        UserDTO userBDTO = new UserDTO();
        userBDTO.setId(2L);
        userBDTO.setUsername("userB");

        UserDTO userCDTO = new UserDTO();
        userCDTO.setId(3L);
        userCDTO.setUsername("userC");

        when(userMapper.toDTO(userB)).thenReturn(userBDTO);
        when(userMapper.toDTO(userC)).thenReturn(userCDTO);

        // Call the service method
        Set<UserDTO> following = userService.getFollowing(1L);

        // Assert that the following field contains 2 users
        assertEquals(2, following.size());
        assertTrue(following.stream().anyMatch(dto -> dto.getUsername().equals("userB")));
        assertTrue(following.stream().anyMatch(dto -> dto.getUsername().equals("userC")));
    }
}