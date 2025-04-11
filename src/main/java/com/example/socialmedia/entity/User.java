package com.example.socialmedia.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString(exclude = {"followers", "following"}) // Exclude followers and following from toString()
@EqualsAndHashCode(exclude = {"followers", "following"}) // Exclude followers and following from equals() and hashCode()
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @ManyToMany
    @JoinTable(
            name = "user_followers",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "follower_id")
    )
    private Set<User> followers = new HashSet<>();

    @ManyToMany(mappedBy = "followers")
    private Set<User> following = new HashSet<>();

    public void addFollower(User follower) {
        this.followers.add(follower);
        follower.getFollowing().add(this);
    }

    public void addFollowing(User following) {
        this.following.add(following);
        following.getFollowers().add(this);
    }
}
