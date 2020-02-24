/**
 * @author: Alexis Mora
 */
package com.esliceu.esliceublogger.manager;

import com.esliceu.esliceublogger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManagerImpl {
    @Autowired
    UserRepository userRepository;
    
}
