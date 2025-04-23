package com.coder.rental.security;//package com.coder.rental.security;


import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

public class CustomerAuthenticationException extends AuthenticationException {
    public CustomerAuthenticationException(String msg) {
        super(msg);
    }
}
