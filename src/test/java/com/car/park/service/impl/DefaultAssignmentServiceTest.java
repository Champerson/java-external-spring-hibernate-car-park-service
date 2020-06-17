package com.car.park.service.impl;

import com.car.park.entities.Assignment;
import com.car.park.entities.Bus;
import com.car.park.entities.Route;
import com.car.park.entities.User;
import com.car.park.repository.AssignmentRepository;
import com.car.park.repository.BusRepository;
import com.car.park.repository.RouteRepository;
import com.car.park.repository.UserRepository;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.time.LocalDateTime.now;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultAssignmentServiceTest {

    private static final long ID = 3;
    private static final boolean ACCEPT_BY_DRIVER_TRUE = true;
    private static final boolean ACCEPT_BY_DRIVER_FALSE = false;

    @InjectMocks
    DefaultAssignmentService defaultAssignmentService;
    @Mock
    private BusRepository busRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private RouteRepository routeRepository;
    @Mock
    private AssignmentRepository assignmentRepository;

    @Mock
    private Assignment assignment;
    @Mock
    private Bus bus;
    @Mock
    private Route route;
    @Mock
    private User user;
    @Mock
    List<Assignment> assignments;

    @Test
    public void shouldAssignBusToRoute() {
        when(busRepository.read(ID)).thenReturn(bus);
        when(routeRepository.read(ID)).thenReturn(route);
        Assignment assignment = new Assignment();
        assignment.setAcceptedByDriver(false);
        assignment.setStartDate(now().toLocalDate());
        assignment.setCreationTime(now());
        assignment.setBus(bus);
        assignment.setRoute(route);

        defaultAssignmentService.assignBusToRoute(ID, ID);

        verify(assignmentRepository).create(assignment);
    }

    @Test
    public void shouldAssignDriverToBus() {
        when(assignmentRepository.read(ID)).thenReturn(assignment);
        when(userRepository.read(ID)).thenReturn(user);

        defaultAssignmentService.assignDriverToBus(ID, ID);

        verify(assignment).setDriver(user);
        verify(assignmentRepository).update(assignment);
    }
    @Test
    public void shouldDeleteAssignmentFromRoute() {
        defaultAssignmentService.deleteAssignmentFromRoute(ID);

        verify(assignmentRepository).delete(ID);
    }

    @Test
    public void shouldAcceptAssignmentByUser() {
        when(user.getAssignment()).thenReturn(assignment);

        defaultAssignmentService.acceptAssignmentByUser(user);

        verify(assignment).setAcceptedByDriver(ACCEPT_BY_DRIVER_TRUE);
        verify(assignmentRepository).update(assignment);
    }

    @Test
    public void shouldDeclineUserAssignment() {
        when(user.getAssignment()).thenReturn(assignment);

        defaultAssignmentService.declineUserAssignment(user);

        verify(assignment).setDriver(null);
        verify(assignment).setAcceptedByDriver(ACCEPT_BY_DRIVER_FALSE);
        verify(user).setAssignment(null);
        verify(assignmentRepository).update(assignment);
    }

    @Test
    public void shouldReturnAssignmentById() {
        when(assignmentRepository.read(ID)).thenReturn(assignment);
        when(assignment.getRoute()).thenReturn(route);
        when(route.getAssignments()).thenReturn(emptyList());

        Assignment resultAssignment = defaultAssignmentService.getAssignmentById(ID);

        assertEquals(assignment, resultAssignment);
    }
}
