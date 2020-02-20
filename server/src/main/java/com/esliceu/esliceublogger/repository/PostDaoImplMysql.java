package com.esliceu.esliceublogger.repository;

import com.esliceu.esliceublogger.entity.Post;
import com.esliceu.esliceublogger.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public class PostDaoImplMysql implements PostDao {
    @Override
    public List<Post> findAll() {
        return null;
    }

    @Override
    public Post findByUser(User user) {
        return null;
    }

    @Override
    public Post findByTitle(String title) {
        return null;
    }

    @Override
    public Post findByContent(String content) {
        return null;
    }

    @Override
    public Post findByDate(Date date) {
        return null;
    }

    @Override
    public void save(Post post) {

    }

    @Override
    public void update(Post post) {

    }

    @Override
    public void delete(Post post) {

    }
}
