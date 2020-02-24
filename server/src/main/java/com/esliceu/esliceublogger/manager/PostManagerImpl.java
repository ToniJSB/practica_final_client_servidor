package com.esliceu.esliceublogger.manager;


import com.esliceu.esliceublogger.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostManagerImpl {

    @Autowired
    PostRepository postDao;
}
