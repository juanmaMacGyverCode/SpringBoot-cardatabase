package com.juanmanuel.cardatabase;

import com.juanmanuel.cardatabase.service.AuthenticationService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AuthenticationFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        //String json = "{ \"username\" : \"admin\", \"password\" : \"admin\" }";
        Authentication authentication = AuthenticationService.getAuthentication((HttpServletRequest) req);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(req, res);
    }
}
