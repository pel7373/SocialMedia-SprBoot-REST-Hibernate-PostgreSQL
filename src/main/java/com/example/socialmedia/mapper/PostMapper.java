package com.example.socialmedia.mapper;

import com.example.socialmedia.dto.PostDTO;
import com.example.socialmedia.entity.Post;
import com.example.socialmedia.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PostMapper {

    // Map the nested property "author.username" to "author" in PostDTO
    @Mapping(target = "author", source = "author.username")
    @Mapping(target = "likes", expression = "java(post.getLikes().stream().map(user -> user.getUsername()).collect(java.util.stream.Collectors.toSet()))")
    PostDTO toDTO(Post post);

    // Map "String author" to "User author" using a custom method
    @Mapping(target = "author", source = "author", qualifiedByName = "mapUsernameToUser")
    @Mapping(target = "likes", source = "likes", qualifiedByName = "mapUsernamesToUsers")
    Post toEntity(PostDTO postDTO);

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
