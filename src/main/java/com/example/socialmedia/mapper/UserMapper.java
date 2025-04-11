package com.example.socialmedia.mapper;

import com.example.socialmedia.dto.UserDTO;
import com.example.socialmedia.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Map "Set<User> followers" to "Set<String> followers" in UserDTO
    @Mapping(target = "followers", expression = "java(user.getFollowers().stream().map(User::getUsername).collect(java.util.stream.Collectors.toSet()))")
    UserDTO toDTO(User user);

    // Map "Set<String> followers" to "Set<User> followers" using a custom method
    @Mapping(target = "followers", source = "followers", qualifiedByName = "mapUsernamesToUsers")
    User toEntity(UserDTO userDTO);

    // Custom method to map "String username" to "User"
    @Named("mapUsernameToUser")
    default User mapUsernameToUser(String username) {
        if (username == null) {
            return null;
        }
        User user = new User();
        user.setUsername(username);
        return user;
    }

    // Custom method to map "Set<String> usernames" to "Set<User>"
    @Named("mapUsernamesToUsers")
    default Set<User> mapUsernamesToUsers(Set<String> usernames) {
        if (usernames == null) {
            return null;
        }
        return usernames.stream().map(this::mapUsernameToUser).collect(Collectors.toSet());
    }
}
