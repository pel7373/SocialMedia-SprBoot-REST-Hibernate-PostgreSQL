package com.example.socialmedia.mapper;

import com.example.socialmedia.dto.UserDTO;
import com.example.socialmedia.entity.User;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserMapperTest {

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Test
    void testToDTO() {
        User user = new User();
        user.setId(1L);
        user.setUsername("john_doe");
        user.setEmail("john.doe@example.com");
        user.setFirstName("John");
        user.setLastName("Doe");

        User follower = new User();
        follower.setUsername("jane_doe");

        Set<User> followers = new HashSet<>();
        followers.add(follower);
        user.setFollowers(followers);

        UserDTO userDTO = userMapper.toDTO(user);

        assertEquals("john_doe", userDTO.getUsername());
        assertEquals(Set.of("jane_doe"), userDTO.getFollowers());
    }

    @Test
    void testToEntity() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("john_doe");
        userDTO.setFollowers(Set.of("jane_doe"));

        User user = userMapper.toEntity(userDTO);

        assertEquals("john_doe", user.getUsername());
        assertEquals(1, user.getFollowers().size());
        assertEquals("jane_doe", user.getFollowers().iterator().next().getUsername());
    }
}
