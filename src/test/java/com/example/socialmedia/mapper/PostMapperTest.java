package com.example.socialmedia.mapper;

import com.example.socialmedia.dto.PostDTO;
import com.example.socialmedia.entity.Post;
import com.example.socialmedia.entity.User;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PostMapperTest {

    private final PostMapper postMapper = Mappers.getMapper(PostMapper.class);

    @Test
    void testToDTO_WithLikesAndAuthor() {
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
    void testToDTO_WithNullLikes() {
        User author = new User();
        author.setUsername("john_doe");

        Post post = new Post();
        post.setId(1L);
        post.setTitle("My First Post");
        post.setBody("This is the body of my first post.");
        post.setAuthor(author);
        post.setLikes(null); // Null likes

        PostDTO postDTO = postMapper.toDTO(post);

        assertEquals("john_doe", postDTO.getAuthor());
        assertNull(postDTO.getLikes()); // Assert null likes
    }

    @Test
    void testToDTO_WithEmptyLikes() {
        User author = new User();
        author.setUsername("john_doe");

        Post post = new Post();
        post.setId(1L);
        post.setTitle("My First Post");
        post.setBody("This is the body of my first post.");
        post.setAuthor(author);
        post.setLikes(new HashSet<>()); // Empty likes

        PostDTO postDTO = postMapper.toDTO(post);

        assertEquals("john_doe", postDTO.getAuthor());
        assertTrue(postDTO.getLikes().isEmpty()); // Assert empty likes
    }

    @Test
    void testToDTO_WithNullAuthor() {
        Post post = new Post();
        post.setId(1L);
        post.setTitle("My First Post");
        post.setBody("This is the body of my first post.");
        post.setAuthor(null); // Null author
        post.setLikes(Set.of(new User()));

        PostDTO postDTO = postMapper.toDTO(post);

        assertNull(postDTO.getAuthor()); // Assert null author
    }

    @Test
    void testToDTO_WithEmptyAuthor() {
        Post post = new Post();
        post.setId(1L);
        post.setTitle("My First Post");
        post.setBody("This is the body of my first post.");
        post.setAuthor(new User()); // Empty author
        post.setLikes(Set.of(new User()));

        PostDTO postDTO = postMapper.toDTO(post);

        assertNull(postDTO.getAuthor()); // Assert empty author maps to null
    }

    @Test
    void testToEntity_WithLikesAndAuthor() {
        PostDTO postDTO = new PostDTO();
        postDTO.setAuthor("john_doe");
        postDTO.setLikes(Set.of("jane_doe"));

        Post post = postMapper.toEntity(postDTO);

        assertEquals("john_doe", post.getAuthor().getUsername());
        assertEquals(1, post.getLikes().size());
        assertEquals("jane_doe", post.getLikes().iterator().next().getUsername());
    }

    @Test
    void testToEntity_WithNullLikes() {
        PostDTO postDTO = new PostDTO();
        postDTO.setAuthor("john_doe");
        postDTO.setLikes(null); // Null likes

        Post post = postMapper.toEntity(postDTO);

        assertEquals("john_doe", post.getAuthor().getUsername());
        assertNull(post.getLikes()); // Assert null likes
    }

    @Test
    void testToEntity_WithEmptyLikes() {
        PostDTO postDTO = new PostDTO();
        postDTO.setAuthor("john_doe");
        postDTO.setLikes(new HashSet<>()); // Empty likes

        Post post = postMapper.toEntity(postDTO);

        assertEquals("john_doe", post.getAuthor().getUsername());
        assertTrue(post.getLikes().isEmpty()); // Assert empty likes
    }

    @Test
    void testToEntity_WithNullAuthor() {
        PostDTO postDTO = new PostDTO();
        postDTO.setAuthor(null); // Null author
        postDTO.setLikes(Set.of("jane_doe"));

        Post post = postMapper.toEntity(postDTO);

        assertNull(post.getAuthor()); // Assert null author
    }

    @Test
    void testToDTO_NullPost() {
        Post post = null; // Null post
        PostDTO postDTO = postMapper.toDTO(post);
        assertNull(postDTO); // Assert null result
    }

    @Test
    void testToEntity_NullPostDTO() {
        PostDTO postDTO = null; // Null PostDTO
        Post post = postMapper.toEntity(postDTO);
        assertNull(post); // Assert null result
    }
}
