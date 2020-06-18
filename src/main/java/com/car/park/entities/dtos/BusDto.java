package com.car.park.entities.dtos;

import com.car.park.web.support.validation.annotations.UniqueBusNumber;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

@UniqueBusNumber(message = "{validation.bus.number.exist}")
public class BusDto {

    public static final String BUS_NUMBER_REGEX = "^[A-Z]{2}\\d{4}[A-Z]{2}$";

    private Long id;

    @NotBlank(message = "{validation.bus.number.empty}")
    @Pattern(message = "{validation.bus.number.invalid}", regexp = BUS_NUMBER_REGEX)
    private String number;

    @Pattern(message = "{validation.bus.model.invalid}", regexp = "(^$|^([a-zA-Z])\\w{0,20}$)")
    private String model;

    @NotBlank(message = "{validation.bus.capacity.empty}")
    @Pattern(message = "{validation.bus.capacity.invalid}", regexp = "^[1-9]\\d{0,2}$")
    private String passengersCapacity;

    @NotBlank(message = "{validation.bus.mileage.empty}")
    @Pattern(message = "{validation.bus.mileage.invalid}", regexp = "^[1-9]\\d{0,5}$")
    private String mileage;

    @Pattern(message = "{validation.bus.colour.en.invalid}", regexp = "(^$|([a-zA-Z\\-]+){0,45})")
    private String colourEn;

    @Pattern(message = "{validation.bus.colour.ua.invalid}", regexp = "(^$|^([\\p{L}\\'\\-]+){0,45})")
    private String colourUa;

    @Pattern(message = "{validation.bus.notes.en.invalid}", regexp = "(^$|([a-zA-Z\\-\\ \\.\\,\\!\\?\\_]+){0,255})")
    private String notesEn;

    @Pattern(message = "{validation.bus.notes.ua.invalid}", regexp = "(^$|([\\p{L}\\'\\-\\ \\.\\,\\!\\?\\_]+){0,255})")
    private String notesUa;

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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPassengersCapacity() {
        return passengersCapacity;
    }

    public void setPassengersCapacity(String passengersCapacity) {
        this.passengersCapacity = passengersCapacity;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getColourEn() {
        return colourEn;
    }

    public void setColourEn(String colourEn) {
        this.colourEn = colourEn;
    }

    public String getColourUa() {
        return colourUa;
    }

    public void setColourUa(String colourUa) {
        this.colourUa = colourUa;
    }

    public String getNotesEn() {
        return notesEn;
    }

    public void setNotesEn(String notesEn) {
        this.notesEn = notesEn;
    }

    public String getNotesUa() {
        return notesUa;
    }

    public void setNotesUa(String notesUa) {
        this.notesUa = notesUa;
    }
}
