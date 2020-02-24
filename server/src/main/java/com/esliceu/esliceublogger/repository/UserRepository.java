package com.esliceu.esliceublogger.repository;

import com.esliceu.esliceublogger.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
