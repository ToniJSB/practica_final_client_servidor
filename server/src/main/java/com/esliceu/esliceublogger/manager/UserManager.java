package com.esliceu.esliceublogger.manager;

import com.esliceu.esliceublogger.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public interface UserManager {

    public void saveOrUpdate(User user);

    public void delete(User user);

    public List<User> getAll();

    public Boolean isValidUser(String email, String password);


}
