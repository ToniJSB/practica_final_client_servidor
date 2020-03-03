package com.esliceu.esliceublogger.controller;

import com.esliceu.esliceublogger.entity.Post;
import com.esliceu.esliceublogger.manager.UserManager;
import com.esliceu.esliceublogger.manager.PostManager;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class PostController {

    @Autowired
    UserManager userManager;

    @Autowired
    PostManager postManager;

    @Autowired
    Gson gson;



    @GetMapping("/posts")
    public ResponseEntity<String> getAll(){

        String jsonObject = gson.toJson(this.postManager.getAll());

        System.out.println(jsonObject);



        return null;
    }






    //FINDBYDATA
    //find author
    //find all

}
