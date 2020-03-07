package com.esliceu.esliceublogger.controller;


import com.esliceu.esliceublogger.entity.Post;
import com.esliceu.esliceublogger.entity.User;
import com.esliceu.esliceublogger.manager.PostManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;

@RestController
public class PostFormController {

    @Autowired
    PostManager postManager;

    @Autowired
    Gson gson;

    @GetMapping("/postForm")
    public ResponseEntity<String> postForm(@RequestParam(name = "id") String id) {
        Post postGetted = postManager.getByIdPost(id);
        Post newPost = new Post();

            Post newPost = new Post();
            newPost.setTitle(postGetted.getTitle());
            newPost.setContent(postGetted.getContent());
            newPost.setLangOriginal(postGetted.getLangOriginal());
            newPost.setLangTranslate(postGetted.getLangTranslate());
            newPost.setDate(postGetted.getDate());

            User user = new User();
            user.setEmail(postGetted.getAuthor().getEmail());
            newPost.setAuthor(user);

            String jsonObjsect = gson.toJson(newPost);
            return new ResponseEntity<>(jsonObjsect, HttpStatus.OK);
        }
        return new ResponseEntity<>("POST NOT FOUND" ,HttpStatus.NOT_FOUND);
    }


}
