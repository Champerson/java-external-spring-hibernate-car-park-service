package com.car.park.web.support.validation;

import com.car.park.entities.Bus;
import com.car.park.entities.dtos.BusDto;
import com.car.park.service.BusService;
import com.car.park.web.support.validation.annotations.UniqueBusNumber;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

import static com.car.park.entities.dtos.BusDto.BUS_NUMBER_REGEX;
import static java.util.regex.Pattern.compile;
import static org.springframework.util.StringUtils.isEmpty;

public class UniqueBusNumberValidator implements ConstraintValidator<UniqueBusNumber, BusDto> {

    private BusService busService;

    @Autowired
    public void setBusService(BusService busService) {
        this.busService = busService;
    }

    @Override
    public void initialize(UniqueBusNumber uniqueBusNumber) {

    }

    @Override
    public boolean isValid(BusDto dto, ConstraintValidatorContext constraintValidatorContext) {
        if (checkValidByRegex(dto.getNumber())) {
            Bus bus = busService.getBusByNumber(dto.getNumber());
            return bus == null || bus.getId().equals(dto.getId());
        } else {
            return true;
        }
    }

    private boolean checkValidByRegex(String value) {
        Pattern pattern = compile(BUS_NUMBER_REGEX);
        return !isEmpty(value) && pattern.matcher(value).matches();
    }
}
