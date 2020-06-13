/**
 * @author: Alexis Mora
 */
package com.esliceu.esliceublogger.manager;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
public class TokenManagerImpl implements TokenManager {

//    @Value("${token.secret_key}")
    private String secret_key = "f3ba752ed9ea26e99821945d4a05e3a01617567c3655e5017ee7cc23b98da390";

    @Override
    public boolean validate(String token) {
        try {

            Algorithm algorithm = Algorithm.HMAC256(this.secret_key);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);

            return jwt != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
