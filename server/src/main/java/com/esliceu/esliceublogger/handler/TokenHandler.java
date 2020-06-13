/**
 * @author: Alexis Mora
 */
package com.esliceu.esliceublogger.handler;

import com.esliceu.esliceublogger.manager.TokenManager;
import com.esliceu.esliceublogger.manager.TokenManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@Component
public class TokenHandler implements HandlerInterceptor {

    @Autowired
    TokenManager tokenManager;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("hacemos preHandle");
        try {
            if (request.getMethod().equals("OPTIONS")) return true;

            String auth = request.getHeader("Authorization");
            if (auth != null && !auth.isEmpty()) {
                String token = auth.replace("Bearer ", "");
                TokenManager tokenManager = new TokenManagerImpl();

                boolean valid = tokenManager.validate(token);
                if (!valid) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return false;
                } else {
                    response.setStatus(HttpServletResponse.SC_OK);
                    return true;
                }
            }else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
        } catch (Exception e) {
            System.out.println("catch");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
        }
        return false;
    }
}
