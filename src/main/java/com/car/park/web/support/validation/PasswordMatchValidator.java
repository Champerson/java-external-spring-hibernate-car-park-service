package com.car.park.web.support.validation;

import com.car.park.entities.User;
import com.car.park.entities.dtos.UserDto;
import com.car.park.service.UserService;
import com.car.park.web.support.PasswordEncoder;
import com.car.park.web.support.validation.annotations.PasswordMatch;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

import static com.car.park.entities.dtos.UserDto.USER_PASSWORD_REGEX;
import static java.util.regex.Pattern.compile;
import static org.springframework.util.StringUtils.isEmpty;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, UserDto> {

    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void initialize(PasswordMatch passwordMatch) {

    }

    @Override
    public boolean isValid(UserDto userDto, ConstraintValidatorContext constraintValidatorContext) {
        User user = userService.getUserById(userDto.getId());
        return !checkValidByRegex(userDto.getPassword()) || passwordEncoder.encode(userDto.getPassword()).equals(user.getPassword());
    }

    private boolean checkValidByRegex(String value) {
        Pattern pattern = compile(USER_PASSWORD_REGEX);
        return !isEmpty(value) && pattern.matcher(value).matches();
    }
}
