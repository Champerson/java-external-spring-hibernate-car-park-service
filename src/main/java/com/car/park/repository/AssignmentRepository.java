package com.car.park.repository;

import com.car.park.entities.Assignment;

import java.util.List;

public interface AssignmentRepository {

    void create(Assignment assignment);

    Assignment read(Long assignmentId);

    Assignment readByBusId(Long busId);

    Assignment readByDriverId(Long driverId);

    List<Assignment> readByRouteId(Long routeId);

    void update(Assignment assignment);

    void delete(Long assignmentId);
}
