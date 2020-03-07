/**
 * @author: Alexis Mora
 */
package com.esliceu.esliceublogger.handler;

import com.esliceu.esliceublogger.manager.TokenManager;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenHandler implements HandlerInterceptor {
    @Autowired
    TokenManager tokenManager;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {

            System.out.println("Entra prehandle");
            String auth = request.getHeader("Authorization");

            if (auth != null && !auth.isEmpty()) {
                String token = auth.replace("Bearer ", "");
                boolean valid = tokenManager.validate(token);
                if (!valid) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return false;
                    //Maybe here we have to redirect to /login
                } else {
                    response.setStatus(HttpServletResponse.SC_OK);
                }
            }
            return true;
        } catch (JwtException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
        }
        return false;
    }
}
