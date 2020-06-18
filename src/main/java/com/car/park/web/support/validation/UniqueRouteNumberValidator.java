package com.car.park.web.support.validation;

import com.car.park.entities.Route;
import com.car.park.entities.dtos.RouteDto;
import com.car.park.service.RouteService;
import com.car.park.web.support.validation.annotations.UniqueRouteNumber;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

import static com.car.park.entities.dtos.RouteDto.ROUTE_NUMBER_REGEX;
import static java.util.regex.Pattern.compile;
import static org.springframework.util.StringUtils.isEmpty;

public class UniqueRouteNumberValidator implements ConstraintValidator<UniqueRouteNumber, RouteDto> {

    private RouteService routeService;

    @Autowired
    public void setRouteService(RouteService routeService) {
        this.routeService = routeService;
    }

    @Override
    public void initialize(UniqueRouteNumber uniqueRouteNumber) {

    }

    @Override
    public boolean isValid(RouteDto dto, ConstraintValidatorContext constraintValidatorContext) {
        if (checkValidByRegex(dto.getNumber())) {
            Route route = routeService.getRouteByNumber(dto.getNumber());
            return route == null || route.getId().equals(dto.getId());
        } else {
            return true;
        }
    }

    private boolean checkValidByRegex(String value) {
        Pattern pattern = compile(ROUTE_NUMBER_REGEX);
        return !isEmpty(value) && pattern.matcher(value).matches();
    }
}
