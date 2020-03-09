/**
 * @author: Alexis Mora
 */
package com.esliceu.esliceublogger.manager;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenManagerImpl implements TokenManager {
   // @Value("${token.secret_key}")
    private String secret_key= "f3ba752ed9ea26e99821945d4a05e3a01617567c3655e5017ee7cc23b98da390";

    @Override
    public boolean validate(String token) {
        try {
        Claims claims = (Claims) Jwts.parser().setSigningKey(secret_key.getBytes()).parseClaimsJws(token).getBody();
            System.out.println("toKEN: "+claims);
            return claims !=null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
