package com.esliceu.esliceublogger.manager;


import com.esliceu.esliceublogger.entity.Post;
import com.esliceu.esliceublogger.entity.User;
import com.esliceu.esliceublogger.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostManagerImpl implements PostManager {

    @Autowired
    PostRepository postDao;

    @Override
    public void save(Post post) {
        postDao.save(post);
    }

    @Override
    public void delete(Post post) {
        postDao.delete(post);
    }

    @Override
    public List<Post> getAll() {
        return (List<Post>) postDao.findAll();
    }

    @Override
    public Post getByIdPost(String id) {
        return postDao.findByIdPost(Long.parseLong(id));
    }

    @Override
    public List<Post> getAllByTitleOrContent(String textToFind) {
        return postDao.findAllByTitleContainsOrContentContains(textToFind, textToFind);
    }

    @Override
    public List<Post> getByAuthor(User author) {
        return postDao.findPostsByAuthor(author);
    }

    @Override
    public List<Post> getByDateBefore(Date date) {
        return postDao.findAllByDateBefore(date);
    }

    @Override
    public List<Post> getByDateAfter(Date date) {
        return postDao.findAllByDateAfter(date);
    }

    @Override
    public List<Post> getByDateBetween(Date dateBefore, Date dateAfter) {
        if (dateBefore.compareTo(dateAfter) > 0) {
            Date tmp = dateAfter;
            dateAfter = dateBefore;
            dateBefore = tmp;
        }
        return postDao.findAllByDateBetween(dateBefore, dateAfter);
    }
}
