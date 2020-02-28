
package com.esliceu.esliceublogger.manager;

import com.esliceu.esliceublogger.entity.Post;
import com.esliceu.esliceublogger.entity.User;
import com.esliceu.esliceublogger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManagerImpl  implements UserManager{
    @Autowired
    UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);}

    @Override
    public void delete(User user) {
        userRepository.delete(user);

    }

    //Futo edit
    @Override
    public void edit(User user) {

    }

    @Override
    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

    //Futura validación
    @Override
    public Boolean isValidUser(String email, String password) {
        return null;
    }

    public List<User> findAllByEmailOrUserName(String s){
        return this.userRepository.findAllByEmailOrUserName(s,s);
    }
}
