/**
 * @author: Alexis Mora
 */
package com.esliceu.esliceublogger.manager;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenManagerImpl implements TokenManager {
    @Value("${token.secret_key}")
    private String secret_key;

    @Override
    public boolean validate(String token) {
        try {
            Claims claims = Jwts.
                    parser().
                    setSigningKey(secret_key.getBytes()).
                    parseClaimsJws(token).
                    getBody();

            return claims != null;
        } catch (Exception e) {
            return false;
        }
    }
}
