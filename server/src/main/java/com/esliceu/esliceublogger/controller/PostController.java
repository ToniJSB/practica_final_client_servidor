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
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

            postO.setAuthor(user);//Here we shouldnt return the entire User object, just the e-meail.

            String jsObject = gson.toJson(postO);
            return jsObject;
        }).collect(Collectors.toList());
        String jsonList = gson.toJson(postsJson);

        return new ResponseEntity<>(jsonList, HttpStatus.OK);
    }

    @GetMapping("/getPost")
    public ResponseEntity<String> postForm(@RequestParam(name = "idPost") String id) {
        System.out.println(id);
        Post postGetted = postManager.getByIdPost(id);
        if (postGetted != null) {

            Post newPost = new Post();
            newPost.setTitle(postGetted.getTitle());
            newPost.setContent(postGetted.getContent());
            if (postGetted.getLangOriginal()==null &&postGetted.getLangTranslate() == null){
                newPost.setLangOriginal("");
                newPost.setLangTranslate("");
            }else{
                newPost.setLangOriginal(postGetted.getLangOriginal());
                newPost.setLangTranslate(postGetted.getLangTranslate());
            }
            newPost.setDate(postGetted.getDate());

            User user = new User();
            user.setEmail(postGetted.getAuthor().getEmail());
            newPost.setAuthor(user);

            String jsonObjsect = gson.toJson(newPost);
            return new ResponseEntity<>(jsonObjsect, HttpStatus.OK);
        }
        return new ResponseEntity<>("POST NOT FOUND" ,HttpStatus.NOT_FOUND);
    }

    @PostMapping("/createPost")
    public ResponseEntity<String> createPost(@RequestBody String body) {

        JsonObject params = gson.fromJson(body,JsonObject.class).get("params").getAsJsonObject();
        Post newPost = new Post();
        newPost.setTitle(params.get("title").getAsString());
        newPost.setContent(params.get("content").getAsString());
        newPost.setLangOriginal(params.get("langOriginal").getAsString());
        newPost.setLangTranslate(params.get("langTranslate").getAsString());

        System.out.println(newPost.getTitle());
        //--Duda--Es mejor pedir la fecha en el servidor o en cliente?
        newPost.setDate(new Date());
        postManager.save(newPost);
        return new ResponseEntity<>(HttpStatus.OK);


    }


    @PutMapping("/updatePost")
    public ResponseEntity<String> updatePost( @RequestBody String body) {
        JsonObject params = gson.fromJson(body,JsonObject.class).get("params").getAsJsonObject();

        String idpost = params.get("idPost").getAsString();
        System.out.println(idpost);
        Post postToUpdate = postManager.getByIdPost(idpost);

        if (postToUpdate != null){
            postToUpdate.setTitle(params.get("title").getAsString());
            postToUpdate.setContent(params.get("content").getAsString());
            postToUpdate.setLangOriginal(params.get("langOriginal").getAsString());
            postToUpdate.setLangTranslate(params.get("langTranslate").getAsString());
            postManager.save(postToUpdate);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>("POST NOT FOUND" ,HttpStatus.NOT_FOUND);
    }


    @GetMapping("/deletePost")
    public ResponseEntity<String> deletePost(@RequestParam(name = "idPost") String idPost) {
        System.out.println(idPost);
        postManager.delete(postManager.getByIdPost(idPost));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
