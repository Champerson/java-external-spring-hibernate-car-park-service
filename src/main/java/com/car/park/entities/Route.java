package com.car.park.entities;

import javax.persistence.*;
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
    private String number;

    @Column(name = "route_length")
    private Integer length;

    @Column(name = "route_description_en")
    private String descriptionEn;

    @Column(name = "route_description_ua")
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
