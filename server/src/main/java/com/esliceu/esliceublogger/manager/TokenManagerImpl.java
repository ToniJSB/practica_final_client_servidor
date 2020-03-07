/**
 * @author: Alexis Mora
 */
package com.esliceu.esliceublogger.manager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
public class TokenManagerImpl implements TokenManager{
    @Value("${token.secret_key}")
    private String secret_key;

    @Override
    public boolean validate(String token) {
           
        return true;
    }
}
