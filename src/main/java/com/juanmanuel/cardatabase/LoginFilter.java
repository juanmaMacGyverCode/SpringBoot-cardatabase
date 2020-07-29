package com.juanmanuel.cardatabase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juanmanuel.cardatabase.domain.AccountCredentials;
import com.juanmanuel.cardatabase.service.AuthenticationService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collections;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {

    public LoginFilter(String url, AuthenticationManager authManager){
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException, IOException, ServletException {
        ////// EL PROBLEMA ESTÁ AQUÍ
        System.out.println("He aquí el problema " + req.getHeader("Authorization"));
        //// ESTO DE AQUI ES EL FUCKING PROBLEMA
        String json = "{ \"username\" : \"admin\", \"password\" : \"admin\" }";
        //////
        /*.readValue(req.getInputStream(), AccountCredentials.class); */
        /* es lo que da problemas, no está recogiendo nada */
        AccountCredentials creds = new ObjectMapper()
                //.readValue(json, AccountCredentials.class);
                // La instrucción de arriba si funciona, PERO NO USARÉ ESO SIEMPRE
                .readValue(req.getInputStream(), AccountCredentials.class);
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        creds.getUsername(),
                        creds.getPassword(),
                        Collections.emptyList()
                )
        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException{
        AuthenticationService.addToken(res, auth.getName());
    }
}
