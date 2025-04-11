-- Insert initial users
INSERT INTO users (username, email, password, first_name, last_name) VALUES
                                                                         ('john_doe', 'john.doe@example.com', 'password123', 'John', 'Doe'),
                                                                         ('jane_doe', 'jane.doe@example.com', 'password456', 'Jane', 'Doe');

-- Insert initial posts
INSERT INTO posts (title, body, author_id) VALUES
                                               ('My First Post', 'This is the body of my first post.', 1),
                                               ('Another Post', 'This is another post by John.', 1),
                                               ('Jane\'s Post', 'This is Jane\'s first post.', 2);

-- Insert initial followers
INSERT INTO user_followers (user_id, follower_id) VALUES
                                                      (1, 2), -- Jane follows John
                                                      (2, 1); -- John follows Jane

-- Insert initial likes
INSERT INTO post_likes (post_id, user_id) VALUES
                                              (1, 2), -- Jane likes John's first post
                                              (3, 1); -- John likes Jane's post