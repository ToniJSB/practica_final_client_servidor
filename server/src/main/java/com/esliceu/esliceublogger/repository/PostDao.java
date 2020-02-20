package com.esliceu.esliceublogger.repository;


import com.esliceu.esliceublogger.entity.Post;
import com.esliceu.esliceublogger.entity.User;

import java.util.Date;
import java.util.List;

public interface PostDao {
    public List<Post> findAll();
    public Post findByUser(User user);
    public Post findByTitle(String title);
    public Post findByContent(String content);
    public Post findByDate(Date date);
    public void save(Post post);
    public void update(Post post);
    public void delete(Post post);
}
