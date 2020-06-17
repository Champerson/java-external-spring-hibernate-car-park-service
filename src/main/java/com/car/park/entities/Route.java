package com.car.park.entities;

import com.car.park.web.support.validation.annotations.UniqueRouteNumber;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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

    public static final String ROUTE_NUMBER_REGEX = "\\d{0,4}";

    @Id
    @Column(name = "route_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "route_number", unique = true, nullable = false)
    @NotBlank(message = "{validation.route.number.empty}")
    @Pattern(message = "{validation.route.number.invalid}", regexp = ROUTE_NUMBER_REGEX)
    @UniqueRouteNumber(message = "{validation.bus.number.exist}")
    private String number;

    @Column(name = "route_length")
    @NotNull(message = "{validation.route.length.empty}")
    @Min(message = "{validation.route.length.invalid}", value = 1)
    @Max(message = "{validation.route.length.invalid}", value = 99)
    private Integer length;

    @Column(name = "route_description_en")
    @NotBlank(message = "{validation.route.description.en.empty}")
    @Pattern(message = "{validation.route.description.en.invalid}", regexp = "([a-zA-Z\\-\\ \\.\\,\\!\\?\\_]+){0,255}")
    private String descriptionEn;

    @Column(name = "route_description_ua")
    @NotBlank(message = "{validation.route.description.ua.empty}")
    @Pattern(message = "{validation.route.description.ua.invalid}", regexp = "([\\p{L}\\'\\-\\ \\.\\,\\!\\?\\_]+){0,255}")
    private String descriptionUa;

    @Column(name = "route_creation_time", nullable = false)
    private LocalDateTime creationTime;

    @OneToMany(mappedBy = "route", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Assignment> assignments;

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
