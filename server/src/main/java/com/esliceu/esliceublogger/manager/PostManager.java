package com.esliceu.esliceublogger.manager;

import com.esliceu.esliceublogger.entity.Post;
import com.esliceu.esliceublogger.entity.User;

import java.util.Date;
import java.util.List;

public interface PostManager {

    public void save(Post post);
    public void delete(Post post);
    public List<Post> getAll();
    public List<Post> getAllByTitleOrContent(String textToFind);
    public List<Post> getByAuthor(User Author);
    public List<Post> getByDateBefore(Date date);
    public List<Post> getByDateAfter(Date date);
    public List<Post> getByDateBetween(Date dateBefore, Date dateAfter);

}