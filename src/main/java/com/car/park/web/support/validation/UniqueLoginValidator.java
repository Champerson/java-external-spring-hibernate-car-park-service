package com.car.park.web.support.validation;

import com.car.park.entities.dtos.UserDto;
import com.car.park.service.UserService;
import com.car.park.web.support.validation.annotations.UniqueLogin;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

import static com.car.park.entities.dtos.UserDto.USER_LOGIN_REGEX;
import static java.util.regex.Pattern.compile;
import static org.springframework.util.StringUtils.isEmpty;

public class UniqueLoginValidator implements ConstraintValidator<UniqueLogin, UserDto> {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(UniqueLogin uniqueLogin) {

    }

    @Override
    public boolean isValid(UserDto dto, ConstraintValidatorContext constraintValidatorContext) {
        if (checkValidByRegex(dto.getLogin())) {
            return userService.getUserByLogin(dto.getLogin()) == null;
        } else {
            return true;
        }
    }

    private boolean checkValidByRegex(String value) {
        Pattern pattern = compile(USER_LOGIN_REGEX);
        return !isEmpty(value) && pattern.matcher(value).matches();
    }
}
