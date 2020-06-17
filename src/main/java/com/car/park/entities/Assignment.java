package com.car.park.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "assignments")
@NamedQueries({
        @NamedQuery(name = "Assignment.readAll", query = "select a from Assignment a"),
        @NamedQuery(name = "Assignment.readByDriverId", query = "select a from Assignment a where a.driver.id = :driverId"),
        @NamedQuery(name = "Assignment.readByBusId", query = "select a from Assignment a where a.bus.id = :busId"),
        @NamedQuery(name = "Assignment.readByRouteId", query = "select a from Assignment a where a.route.id = :routeId")
})
public class Assignment {

    @Id
    @Column(name = "assignment_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "assignment_accepted", nullable = false, columnDefinition = "TINYINT")
    private Boolean acceptedByDriver;

    @Column(name = "assignment_creation_time", nullable = false)
    private LocalDateTime creationTime;

    @Column(name = "assignment_start_date", nullable = false)
    private LocalDate startDate;

    @OneToOne
    @JoinColumn(name = "assignment_bus_id")
    private Bus bus;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "assignment_route_id")
    private Route route;

    @OneToOne
    @JoinColumn(name = "assignment_user_id")
    private User driver;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAcceptedByDriver() {
        return acceptedByDriver;
    }

    public void setAcceptedByDriver(Boolean acceptedByDriver) {
        this.acceptedByDriver = acceptedByDriver;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assignment that = (Assignment) o;
        return acceptedByDriver.equals(that.acceptedByDriver) &&
                creationTime.equals(that.creationTime) &&
                startDate.equals(that.startDate) &&
                Objects.equals(bus, that.bus) &&
                Objects.equals(route, that.route) &&
                Objects.equals(driver, that.driver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(acceptedByDriver, creationTime, startDate, bus, route, driver);
    }
}
