package com.car.park.service;

import com.car.park.entities.Assignment;
import com.car.park.entities.User;

public interface AssignmentService {

    void assignBusToRoute(Long routeId, Long busId);

    void assignDriverToBus(Long userId, Long assignmentId);

    void deleteAssignmentFromRoute(Long assignmentId);

    void acceptAssignmentByUser(User user);

    void declineUserAssignment(User user);

    Assignment getAssignmentById(Long assignmentId);
}
