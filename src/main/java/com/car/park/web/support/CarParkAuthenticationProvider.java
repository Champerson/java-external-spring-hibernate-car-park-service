package com.car.park.web.support;

import com.car.park.entities.User;
import com.car.park.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.car.park.entities.dtos.UserDto.USER_LOGIN_REGEX;
import static com.car.park.entities.dtos.UserDto.USER_PASSWORD_REGEX;
import static java.util.Collections.singletonList;
import static java.util.regex.Pattern.compile;
import static org.springframework.util.StringUtils.isEmpty;

@Component("authenticationProvider")
public class CarParkAuthenticationProvider implements AuthenticationProvider {

    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String password = authentication.getCredentials().toString();
        checkCredentialsValid(login, password);

        User user = userService.getUserByLogin(login);
        checkCredentialsCorrect(user, password);

        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getAccessRole().name());
        return new UsernamePasswordAuthenticationToken(login, password, singletonList(grantedAuthority));
    }

    private void checkCredentialsValid(String login, String password) {
        boolean loginNotValid = isEmpty(login) || !compile(USER_LOGIN_REGEX).matcher(login).matches();
        boolean passwordNotValid = isEmpty(password) || !compile(USER_PASSWORD_REGEX).matcher(password).matches();
        if (loginNotValid || passwordNotValid) {
            throw new BadCredentialsException("Bad credentials");
        }
    }

    private void checkCredentialsCorrect(User user, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        if (user == null || !user.getPassword().equals(encodedPassword)) {
            throw new BadCredentialsException("Bad credentials");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
