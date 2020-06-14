package com.car.park.entities;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "routes")

@NamedQueries({
        @NamedQuery(name = "Route.readAll", query = "select r from Route r"),
        @NamedQuery(name = "Route.readByNumber", query = "select r from Route r where r.number = :number")
})
public class Route {

    @Id
    @Column(name = "route_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "route_number", unique = true, nullable = false)
    @NotBlank(message = "Number cannot be blank.")
    @Pattern(regexp="\\d{0,4}", message="Number is not valid.")
    private String number;

    @Column(name = "route_length")
    @Digits(message = "Format of number must be x(1-9)", integer = 9, fraction = 0)
    //@Pattern(regexp="^\\d{0,9}$", message="Length is not valid.")
    private Integer length;

    @Column(name = "route_creation_time", nullable = false)
    private LocalDateTime creationTime;

    @OneToMany(mappedBy = "route", cascade = CascadeType.MERGE)
    private List<Assignment> assignments;

    @Column(name = "route_description_en")
    @Pattern(regexp="([a-zA-Z\\-\\ \\.\\,\\!\\?\\_]+){0,255}", message="Description in English is not valid.")
    private String descriptionEn;

    @Column(name = "route_description_ua")
    @Pattern(regexp="([\\p{L}\\'\\-\\ \\.\\,\\!\\?\\_]+){0,255}", message="Description in Ukrainian is not valid.")
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

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
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
