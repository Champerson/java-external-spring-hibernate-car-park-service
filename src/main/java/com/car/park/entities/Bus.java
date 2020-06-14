package com.car.park.entities;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Entity
@Table(name = "buses")
@NamedQueries({
        @NamedQuery(name = "Bus.readAll", query = "select b from Bus b"),
        @NamedQuery(name = "Bus.readByNumber", query = "select b from Bus b where b.number = :number")
})
public class Bus {

    @Id
    @Column(name = "bus_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bus_number", unique = true, nullable = false)
    @NotBlank(message = "Number cannot be blank.")
    @Pattern(regexp="^[A-Z]{2}\\d{4}[A-Z]{2}$", message="Number is not valid.")
    private String number;

    @Column(name = "bus_model")
    @NotEmpty(message = "Model cannot be empty.")
    @Pattern(regexp="^([a-zA-Z])\\w{0,20}$", message="Model is not valid.")
    private String model;

    @Column(name = "bus_passengers_capacity")
    @Digits(message = "Format of number must be xxx", integer = 3, fraction = 0)
    //@Pattern(regexp="^\\d{1,3}$", message="Passenger capacity is not valid.")
    private Integer passengersCapacity;

    @Column(name = "bus_mileage")
    @Digits(message = "Format of number must be x(1-9)", integer = 9, fraction = 0)
    //@Pattern(regexp="^\\d{1,9}$", message="Mileage is not valid.")
    private Integer mileage;

    @Column(name = "bus_colour_en")
    @Pattern(regexp="([a-zA-Z\\-]+){0,45}", message="Colour in English is not valid.")
    private String colourEn;

    @Column(name = "bus_colour_ua")
    @Pattern(regexp="^([\\p{L}\\'\\-]+){0,45}", message="Colour in Ukrainian is not valid.")
    private String colourUa;

    @Column(name = "bus_notes_en")
    @Pattern(regexp="([a-zA-Z\\-\\ \\.\\,\\!\\?\\_]+){0,255}", message="Notes in English are not valid.")
    private String notesEn;

    @Column(name = "bus_notes_ua")
    @Pattern(regexp="([\\p{L}\\'\\-\\ \\.\\,\\!\\?\\_]+){0,255}", message="Notes in Ukrainian are not valid.")
    private String notesUa;

    @Column(name = "bus_creation_time", nullable = false)
    private LocalDateTime creationTime;

    @OneToOne(mappedBy = "bus")
    private Assignment assignment;

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

    public Integer getPassengersCapacity() {
        return passengersCapacity;
    }

    public void setPassengersCapacity(Integer passengersCapacity) {
        this.passengersCapacity = passengersCapacity;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
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

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }
}
