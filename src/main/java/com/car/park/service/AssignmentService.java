package com.car.park.service;

import com.car.park.entities.Assignment;
import com.car.park.entities.User;

/**
 * Interacts with part of repository's layer which is responsible for assignment.
 * @see User
 * @see Assignment
 */
public interface AssignmentService {

    /**
     * Assign available bus to route by given bus and route ids
     * @param routeId route id
     * @param busId bus id
     */
    void assignBusToRoute(Long routeId, Long busId);

    /**
     * Assign available driver to bus by given driver(user) and assignment ids
     * @param userId user id
     * @param assignmentId assignment id
     */
    void assignDriverToBus(Long userId, Long assignmentId);

    /**
     * Delete assignment from route by given assignment id
     * @param assignmentId assignment id
     */
    void deleteAssignmentFromRoute(Long assignmentId);

    /**
     * Accept user assignment by given user instance
     * @param user user instance
     */
    void acceptAssignmentByUser(User user);

    /**
     * Decline user assignment by user instance
     * @param user user instacne
     */
    void declineUserAssignment(User user);

    /**
     * Get assignment by given id
     * @param assignmentId assignment id
     * @return assignment
     */
    Assignment getAssignmentById(Long assignmentId);
}
