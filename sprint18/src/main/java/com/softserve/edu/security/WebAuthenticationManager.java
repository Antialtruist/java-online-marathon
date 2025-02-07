package com.softserve.edu.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class WebAuthenticationManager implements AuthenticationManager {
    private List<AuthenticationProvider> authenticationProviders;

    @Lazy
    @Autowired
    public void setAuthenticationProviders(List<AuthenticationProvider> authenticationProviders) {
        this.authenticationProviders = authenticationProviders;
    }

    @Override
    public Authentication authenticate(Authentication authentication) {
        Authentication webAuthentication;
        for (AuthenticationProvider authenticationProvider : authenticationProviders) {
            webAuthentication = authenticationProvider.authenticate(authentication);
            if (webAuthentication != null) {
                webAuthentication.setAuthenticated(true);
                return webAuthentication;
            }
        }
        throw new BadCredentialsException("Bad user Credentials!");
    }
}
