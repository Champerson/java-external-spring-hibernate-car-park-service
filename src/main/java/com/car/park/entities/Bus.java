package com.car.park.entities;

import com.car.park.web.support.validation.annotations.UniqueBusNumber;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Entity
@Table(name = "buses")
@NamedQueries({
        @NamedQuery(name = "Bus.readAll", query = "select b from Bus b"),
        @NamedQuery(name = "Bus.readByNumber", query = "select b from Bus b where b.number = :number")
})
public class Bus {

    public static final String BUS_NUMBER_REGEX = "^[A-Z]{2}\\d{4}[A-Z]{2}$";

    @Id
    @Column(name = "bus_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bus_number", unique = true, nullable = false)
    @NotBlank(message = "{validation.bus.number.empty}")
    @Pattern(message = "{validation.bus.number.invalid}", regexp = BUS_NUMBER_REGEX)
    @UniqueBusNumber(message = "{validation.route.number.exist}")
    private String number;

    @Column(name = "bus_model")
    @Pattern(message = "{validation.bus.model.invalid}", regexp = "(^$|^([a-zA-Z])\\w{0,20}$)")
    private String model;

    @Column(name = "bus_passengers_capacity")
    @NotNull(message = "{validation.bus.capacity.empty}")
    @Min(message = "{validation.bus.capacity.invalid}", value = 1)
    @Max(message = "{validation.bus.capacity.invalid}", value = 999)
    private Integer passengersCapacity;

    @Column(name = "bus_mileage")
    @NotNull(message = "{validation.bus.mileage.empty}")
    @Min(message = "{validation.bus.mileage.invalid}", value = 0)
    @Max(message = "{validation.bus.mileage.invalid}", value = 999999)
    private Integer mileage;

    @Column(name = "bus_colour_en")
    @Pattern(message = "{validation.bus.colour.en.invalid}", regexp = "(^$|([a-zA-Z\\-]+){0,45})")
    private String colourEn;

    @Column(name = "bus_colour_ua")
    @Pattern(message = "{validation.bus.colour.ua.invalid}", regexp = "(^$|^([\\p{L}\\'\\-]+){0,45})")
    private String colourUa;

    @Column(name = "bus_notes_en")
    @Pattern(message = "{validation.bus.notes.en.invalid}", regexp = "(^$|([a-zA-Z\\-\\ \\.\\,\\!\\?\\_]+){0,255})")
    private String notesEn;

    @Column(name = "bus_notes_ua")
    @Pattern(message = "{validation.bus.notes.ua.invalid}", regexp = "(^$|([\\p{L}\\'\\-\\ \\.\\,\\!\\?\\_]+){0,255})")
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
