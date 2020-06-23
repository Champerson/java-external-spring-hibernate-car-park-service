package com.car.park.entities.dtos;

import com.car.park.web.support.validation.annotations.UniqueRouteNumber;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * DTO, contains information of route
 */
@UniqueRouteNumber(message = "{validation.route.number.exist}")
public class RouteDto {

    public static final String ROUTE_NUMBER_REGEX = "\\d{0,4}";

    private Long id;

    @NotBlank(message = "{validation.route.number.empty}")
    @Pattern(message = "{validation.route.number.invalid}", regexp = ROUTE_NUMBER_REGEX)
    private String number;

    @NotBlank(message = "{validation.route.length.empty}")
    @Pattern(message = "{validation.route.length.invalid}", regexp = "^[1-9]\\d?$")
    private String length;

    @NotBlank(message = "{validation.route.description.en.empty}")
    @Pattern(message = "{validation.route.description.en.invalid}", regexp = "([a-zA-Z\\-\\ \\.\\,\\!\\?\\_]+){0,255}")
    private String descriptionEn;

    @NotBlank(message = "{validation.route.description.ua.empty}")
    @Pattern(message = "{validation.route.description.ua.invalid}", regexp = "([\\p{L}\\'\\-\\ \\.\\,\\!\\?\\_]+){0,255}")
    private String descriptionUa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public String getDescriptionUa() {
        return descriptionUa;
    }

    public void setDescriptionUa(String descriptionUa) {
        this.descriptionUa = descriptionUa;
    }
}
