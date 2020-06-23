package com.car.park.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

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
    private String number;

    @Column(name = "bus_model")
    private String model;

    @Column(name = "bus_passengers_capacity")
    private Integer passengersCapacity;

    @Column(name = "bus_mileage")
    private Integer mileage;

    @Column(name = "bus_colour_en")
    private String colourEn;

    @Column(name = "bus_colour_ua")
    private String colourUa;

    @Column(name = "bus_notes_en")
    private String notesEn;

    @Column(name = "bus_notes_ua")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bus bus = (Bus) o;
        return Objects.equals(id, bus.id) &&
                Objects.equals(number, bus.number) &&
                Objects.equals(model, bus.model) &&
                Objects.equals(passengersCapacity, bus.passengersCapacity) &&
                Objects.equals(mileage, bus.mileage) &&
                Objects.equals(colourEn, bus.colourEn) &&
                Objects.equals(colourUa, bus.colourUa) &&
                Objects.equals(notesEn, bus.notesEn) &&
                Objects.equals(notesUa, bus.notesUa) &&
                Objects.equals(creationTime, bus.creationTime) &&
                Objects.equals(assignment, bus.assignment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, model, passengersCapacity, mileage, colourEn, colourUa, notesEn, notesUa, creationTime, assignment);
    }
}
