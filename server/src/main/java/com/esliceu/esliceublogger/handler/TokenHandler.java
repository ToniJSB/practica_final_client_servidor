/**
 * @author: Alexis Mora
 */
package com.esliceu.esliceublogger.handler;

import com.esliceu.esliceublogger.manager.TokenManager;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class TokenHandler implements HandlerInterceptor {
    @Autowired
    TokenManager tokenManager;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {

            String auth = request.getHeader("Authorization");
            if (auth != null && !auth.isEmpty()) {
                String token = auth.replace("Bearer ", "");
                boolean valid = tokenManager.validate(token);
                if (!valid) {
                    System.out.println("No es valido");
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return false;
                } else {
                    System.out.println("Aqui");
                    response.setStatus(HttpServletResponse.SC_OK);
                }
            }else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
        } catch (JwtException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
        }
        return false;
    }
}
