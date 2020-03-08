package com.esliceu.esliceublogger.controller;


import com.esliceu.esliceublogger.entity.Post;
import com.esliceu.esliceublogger.entity.User;
import com.esliceu.esliceublogger.manager.PostManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;

import java.util.IllegalFormatCodePointException;

@RestController
public class PostFormController {

    @Autowired
    PostManager postManager;

    @Autowired
    Gson gson;

    @GetMapping("/postForm")
    public ResponseEntity<String> postForm(@RequestParam(name = "id") String id) {

        Post postGetted = postManager.getByIdPost(id);
        if (postGetted != null) {

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

    @DeleteMapping("/deletePost")
    public ResponseEntity<String> deletePost(@RequestParam(name = "idPost") String idPost) {
        postManager.delete(postManager.getByIdPost(idPost));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/UpdatePost")
    public ResponseEntity<String> updatePost(
            @RequestParam(name = "idPost") String idPost,
            @RequestParam(name = "title") String title,
            @RequestParam(name = "content") String content,
            @RequestParam(name = "langOriginal") String langOriginal,
            @RequestParam(name = "langTranslate") String langTranslate) {
        Post postToUpdate = postManager.getByIdPost(idPost);
        if (postToUpdate != null){
            postToUpdate.setTitle(title);
            postToUpdate.setContent(content);
            postToUpdate.setLangOriginal(langOriginal);
            postToUpdate.setLangTranslate(langTranslate);
            postManager.save(postToUpdate);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>("POST NOT FOUND" ,HttpStatus.NOT_FOUND);
    }
}

