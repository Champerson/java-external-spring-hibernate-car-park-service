package com.car.park.service.impl;

import com.car.park.entities.Assignment;
import com.car.park.entities.User;
import com.car.park.repository.AssignmentRepository;
import com.car.park.repository.BusRepository;
import com.car.park.repository.RouteRepository;
import com.car.park.repository.UserRepository;
import com.car.park.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.time.LocalDateTime.now;

@Service
public class DefaultAssignmentService implements AssignmentService {

    private BusRepository busRepository;
    private UserRepository userRepository;
    private RouteRepository routeRepository;
    private AssignmentRepository assignmentRepository;

    @Override
    @Transactional
    public void assignBusToRoute(Long routeId, Long busId) {
        Assignment assignment = new Assignment();
        assignment.setAcceptedByDriver(false);
        assignment.setStartDate(now().toLocalDate());
        assignment.setCreationTime(now());
        assignment.setBus(busRepository.read(busId));
        assignment.setRoute(routeRepository.read(routeId));
        assignmentRepository.create(assignment);
    }

    @Override
    @Transactional
    public void assignDriverToBus(Long userId, Long assignmentId) {
        Assignment assignment = assignmentRepository.read(assignmentId);
        assignment.setDriver(userRepository.read(userId));
        assignmentRepository.update(assignment);
    }

    @Override
    @Transactional
    public void deleteAssignmentFromRoute(Long assignmentId) {
        assignmentRepository.delete(assignmentId);
    }

    @Override
    @Transactional
    public void acceptAssignmentByUser(User user) {
        Assignment assignment = user.getAssignment();
        assignment.setAcceptedByDriver(true);
        assignmentRepository.update(assignment);
    }

    @Override
    @Transactional
    public void declineUserAssignment(User user) {
        Assignment assignment = user.getAssignment();
        assignment.setDriver(null);
        assignment.setAcceptedByDriver(false);
        user.setAssignment(null);
        assignmentRepository.update(assignment);
    }

    @Override
    @Transactional(readOnly = true)
    public Assignment getAssignmentById(Long assignmentId) {
        Assignment assignment = assignmentRepository.read(assignmentId);
        assignment.getRoute().getAssignments().iterator();
        return assignment;
    }

    @Autowired
    public void setBusRepository(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRouteRepository(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @Autowired
    public void setAssignmentRepository(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }
}
