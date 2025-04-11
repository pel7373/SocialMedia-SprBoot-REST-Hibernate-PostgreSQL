package com.example.socialmedia.mapper;

import com.example.socialmedia.dto.PostDTO;
import com.example.socialmedia.entity.Post;
import com.example.socialmedia.entity.User;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PostMapperTest {

    private final PostMapper postMapper = Mappers.getMapper(PostMapper.class);

    @Test
    void testToDTO() {
        User author = new User();
        author.setUsername("john_doe");

        User liker = new User();
        liker.setUsername("jane_doe");

        Set<User> likes = new HashSet<>();
        likes.add(liker);

        Post post = new Post();
        post.setId(1L);
        post.setTitle("My First Post");
        post.setBody("This is the body of my first post.");
        post.setAuthor(author);
        post.setLikes(likes);

        PostDTO postDTO = postMapper.toDTO(post);

        assertEquals("john_doe", postDTO.getAuthor());
        assertEquals(Set.of("jane_doe"), postDTO.getLikes());
    }

    @Test
    void testToEntity() {
        PostDTO postDTO = new PostDTO();
        postDTO.setAuthor("john_doe");
        postDTO.setLikes(Set.of("jane_doe"));

        Post post = postMapper.toEntity(postDTO);

        assertEquals("john_doe", post.getAuthor().getUsername());
        assertEquals(1, post.getLikes().size());
        assertEquals("jane_doe", post.getLikes().iterator().next().getUsername());
    }
}
