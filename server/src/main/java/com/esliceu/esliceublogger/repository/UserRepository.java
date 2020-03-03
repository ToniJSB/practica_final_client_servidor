package com.esliceu.esliceublogger.repository;

import com.esliceu.esliceublogger.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

        List<User> getByEmailOrUserName(String s ,String s2);

}
