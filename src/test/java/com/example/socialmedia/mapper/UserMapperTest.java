package com.example.socialmedia.mapper;

import com.example.socialmedia.dto.UserDTO;
import com.example.socialmedia.entity.User;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Test
    void testToDTO_WithFollowers() {
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
    void testToDTO_WithNullFollowers() {
        User user = new User();
        user.setId(1L);
        user.setUsername("john_doe");
        user.setEmail("john.doe@example.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setFollowers(null); // Null followers

        UserDTO userDTO = userMapper.toDTO(user);

        assertEquals("john_doe", userDTO.getUsername());
        assertNull(userDTO.getFollowers()); // Assert null followers
    }

    @Test
    void testToDTO_WithEmptyFollowers() {
        User user = new User();
        user.setId(1L);
        user.setUsername("john_doe");
        user.setEmail("john.doe@example.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setFollowers(new HashSet<>()); // Empty followers

        UserDTO userDTO = userMapper.toDTO(user);

        assertEquals("john_doe", userDTO.getUsername());
        assertTrue(userDTO.getFollowers().isEmpty()); // Assert empty followers
    }

    @Test
    void testToEntity_WithFollowers() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("john_doe");
        userDTO.setFollowers(Set.of("jane_doe"));

        User user = userMapper.toEntity(userDTO);

        assertEquals("john_doe", user.getUsername());
        assertEquals(1, user.getFollowers().size());
        assertEquals("jane_doe", user.getFollowers().iterator().next().getUsername());
    }

    @Test
    void testToEntity_WithNullFollowers() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("john_doe");
        userDTO.setFollowers(null); // Null followers

        User user = userMapper.toEntity(userDTO);

        assertEquals("john_doe", user.getUsername());
        assertNull(user.getFollowers()); // Assert null followers
    }

    @Test
    void testToEntity_WithEmptyFollowers() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("john_doe");
        userDTO.setFollowers(new HashSet<>()); // Empty followers

        User user = userMapper.toEntity(userDTO);

        assertEquals("john_doe", user.getUsername());
        assertTrue(user.getFollowers().isEmpty()); // Assert empty followers
    }

    @Test
    void testToDTO_WithNullFields() {
        User user = new User();
        user.setId(null); // Null ID
        user.setUsername(null); // Null username
        user.setEmail(null); // Null email
        user.setFirstName(null); // Null firstName
        user.setLastName(null); // Null lastName
        user.setFollowers(null); // Null followers

        UserDTO userDTO = userMapper.toDTO(user);

        assertNull(userDTO.getUsername());
        assertNull(userDTO.getFollowers());
    }

    @Test
    void testToEntity_WithNullFields() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(null); // Null username
        userDTO.setFollowers(null); // Null followers

        User user = userMapper.toEntity(userDTO);

        assertNull(user.getUsername());
        assertNull(user.getFollowers());
    }

    @Test
    void testToDTO_NullUser() {
        User user = null; // Null user
        UserDTO userDTO = userMapper.toDTO(user);
        assertNull(userDTO); // Assert null result
    }

    @Test
    void testToEntity_NullUserDTO() {
        UserDTO userDTO = null; // Null UserDTO
        User user = userMapper.toEntity(userDTO);
        assertNull(user); // Assert null result
    }

    @Test
    void testMapUsernameToUser_NullUsername() {
        User user = userMapper.mapUsernameToUser(null); // Null username
        assertNull(user); // Assert null result
    }
}
