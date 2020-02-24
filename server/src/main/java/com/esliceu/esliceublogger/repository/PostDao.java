package com.esliceu.esliceublogger.repository;


import com.esliceu.esliceublogger.entity.Post;
import com.esliceu.esliceublogger.entity.User;
import javafx.geometry.Pos;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface PostDao extends CrudRepository<Post,Integer> {
    public List<Post> findAllByTitleContainsOrContentContains(String textToFind);
    public List<Post> findAllByAuthor(String author);
    public List<Post> findAllByDateBefore(Date date);
    public List<Post> findAllByDateAfter(Date date);

}
