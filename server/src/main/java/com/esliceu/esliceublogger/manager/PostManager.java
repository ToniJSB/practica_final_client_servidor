package com.esliceu.esliceublogger.manager;

import com.esliceu.esliceublogger.entity.Post;

import java.util.Date;
import java.util.List;

public interface PostManager {

    public void save(Post post);
    public void delete(Post post);
    public List<Post> getAll();
    public List<Post> getAllByTitleOrContent(String textToFind);
    public List<Post> findByAuthor(String Author);
    public List<Post> findByDateBefore(Date date);
    public List<Post> findByDateAfter(Date date);
    public List<Post> findByDateBetween(Date dateBefore, Date dateAfter);

}