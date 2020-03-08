package com.esliceu.esliceublogger.controller;

import com.esliceu.esliceublogger.entity.Post;
import com.esliceu.esliceublogger.entity.User;
import com.esliceu.esliceublogger.manager.TokenManager;
import com.esliceu.esliceublogger.manager.UserManager;
import com.esliceu.esliceublogger.manager.PostManager;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PostController {

    @Autowired
    UserManager userManager;

    @Autowired
    PostManager postManager;

    @Autowired
    Gson gson;


    @GetMapping("/posts")
    public ResponseEntity<String> getAll() {
        List<Post> posts = this.postManager.getAll();
        System.out.println(posts);
        JsonArray jsonArray = new JsonArray();
        List<String> postsJson = posts.stream().map(post -> {
            Post postO = new Post();
            postO.setIdPost(post.getIdPost());
            postO.setTitle(post.getTitle());
            postO.setContent(post.getContent());
            postO.setDate(post.getDate());
            postO.setLangOriginal(post.getLangOriginal());
            postO.setLangTranslate(post.getLangTranslate());

            User user = new User();
            user.setIdUser(post.getAuthor().getIdUser());
            user.setEmail(post.getAuthor().getEmail());
            user.setFirstName(post.getAuthor().getFirstName());
            user.setLastName(post.getAuthor().getLastName());
            user.setPassword(post.getAuthor().getPassword());

            postO.setAuthor(user);//Here we shouldnt return the entire User object, just the e-meail.

            String jsObject = gson.toJson(postO);
            jsonArray.add(jsObject);
            return jsObject;
        }).collect(Collectors.toList());

        StringBuilder json = new StringBuilder();
        for (String string : postsJson) {
            json.append(string);
        }
        System.out.println(json.toString());
        System.out.println(jsonArray);

        return new ResponseEntity<>(json.toString(), HttpStatus.OK);
    }


    //FINDBYDATA
    //find author
    //find all


    //FINDBYDATA
    //find author
    //find all

}
