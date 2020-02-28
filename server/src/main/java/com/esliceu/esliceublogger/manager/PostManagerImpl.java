package com.esliceu.esliceublogger.manager;


import com.esliceu.esliceublogger.entity.Post;
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
    public void save(Post post) { postDao.save(post); }

    @Override
    public void delete(Post post) {
        postDao.delete(post);
    }

    @Override
    public List<Post> getAll() {
        return (List<Post>) postDao.findAll();
    }

    @Override
    public List<Post> getAllByTitleOrContent(String textToFind) {
        return postDao.findAllByTitleContainsOrContentContains(textToFind,textToFind);
    }

    @Override
    public List<Post> findByAuthor(String author) {
        return postDao.findAllByAuthor(author);
    }

    @Override
    public List<Post> findByDateBefore(Date date) {
        return postDao.findAllByDateBefore(date);
    }

    @Override
    public List<Post> findByDateAfter(Date date) {
        return postDao.findAllByDateAfter(date);
    }

    @Override
    public List<Post> findByDateBetween(Date dateBefore, Date dateAfter) {
        if (dateBefore.compareTo(dateAfter) > 0){
            Date tmp = dateAfter;
            dateAfter = dateBefore;
            dateBefore=tmp;
        }
        return postDao.findAllByDateBeforeBetweenDateAfter(dateBefore,dateAfter);
    }
}
