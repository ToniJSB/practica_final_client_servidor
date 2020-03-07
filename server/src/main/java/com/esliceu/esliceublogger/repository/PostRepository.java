package com.esliceu.esliceublogger.repository;


import com.esliceu.esliceublogger.entity.Post;
import com.esliceu.esliceublogger.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface PostRepository extends CrudRepository<Post,Integer> {
    public List<Post> findAllByTitleContainsOrContentContains(String textToFind, String textToFind2);
    public List<Post> findPostsByAuthor(User author);
    public Post findByIdPost(long id);
    public List<Post> findAllByDateBefore(Date date);
    public List<Post> findAllByDateAfter(Date date);
    public List<Post> findAllByDateBetween(Date dateBefore,Date dateAfter);

}
