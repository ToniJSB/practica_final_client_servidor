package com.esliceu.esliceublogger.controller;


import com.esliceu.esliceublogger.manager.PostManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;

@RestController
public class PostFormController {

    @Autowired
    PostManager postManager;

    @Autowired
    Gson gson;

}
