/**
 * @author: Alexis Mora
 */
package com.esliceu.esliceublogger.controller;

import com.esliceu.esliceublogger.manager.UserManager;
import com.esliceu.esliceublogger.manager.PostManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    @Autowired
    UserManager userManager;

    @Autowired
    PostManager postManager;


}
