package com.car.park.service;

import com.car.park.entities.User;
import com.car.park.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Collections.singletonList;

@Component("authenticationProvider")
public class CarParkAuthenticationProvider implements AuthenticationProvider {

    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = userRepository.read(login);

        if (user == null) {
            throw new BadCredentialsException("Bad credentials");
        } else {
            SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getAccessRole().name());
            return new UsernamePasswordAuthenticationToken(login, password, singletonList(grantedAuthority));
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
