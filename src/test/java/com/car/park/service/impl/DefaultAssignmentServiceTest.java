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
    private BusRepository busRepositoryMock;
    @Mock
    private UserRepository userRepositoryMock;
    @Mock
    private RouteRepository routeRepositoryMock;
    @Mock
    private AssignmentRepository assignmentRepositoryMock;

    @Mock
    private Assignment assignmentMock;
    @Mock
    private Bus busMock;
    @Mock
    private Route routeMock;
    @Mock
    private User userMock;
    @Mock
    List<Assignment> assignments;

    @Test
    public void shouldAssignBusToRoute() {
        when(busRepositoryMock.read(ID)).thenReturn(busMock);
        when(routeRepositoryMock.read(ID)).thenReturn(routeMock);
        Assignment assignment = new Assignment();
        assignment.setAcceptedByDriver(false);
        assignment.setStartDate(now().toLocalDate());
        assignment.setCreationTime(now());
        assignment.setBus(busMock);
        assignment.setRoute(routeMock);

        defaultAssignmentService.assignBusToRoute(ID, ID);

        verify(assignmentRepositoryMock).create(assignment);
    }

    @Test
    public void shouldAssignDriverToBus() {
        when(assignmentRepositoryMock.read(ID)).thenReturn(assignmentMock);
        when(userRepositoryMock.read(ID)).thenReturn(userMock);

        defaultAssignmentService.assignDriverToBus(ID, ID);

        verify(assignmentMock).setDriver(userMock);
        verify(assignmentRepositoryMock).update(assignmentMock);
    }
    @Test
    public void shouldDeleteAssignmentFromRoute() {
        defaultAssignmentService.deleteAssignmentFromRoute(ID);

        verify(assignmentRepositoryMock).delete(ID);
    }

    @Test
    public void shouldAcceptAssignmentByUser() {
        when(userMock.getAssignment()).thenReturn(assignmentMock);

        defaultAssignmentService.acceptAssignmentByUser(userMock);

        verify(assignmentMock).setAcceptedByDriver(ACCEPT_BY_DRIVER_TRUE);
        verify(assignmentRepositoryMock).update(assignmentMock);
    }

    @Test
    public void shouldDeclineUserAssignment() {
        when(userMock.getAssignment()).thenReturn(assignmentMock);

        defaultAssignmentService.declineUserAssignment(userMock);

        verify(assignmentMock).setDriver(null);
        verify(assignmentMock).setAcceptedByDriver(ACCEPT_BY_DRIVER_FALSE);
        verify(userMock).setAssignment(null);
        verify(assignmentRepositoryMock).update(assignmentMock);
    }

    @Test
    public void shouldReturnAssignmentById() {
        when(assignmentRepositoryMock.read(ID)).thenReturn(assignmentMock);
        when(assignmentMock.getRoute()).thenReturn(routeMock);
        when(routeMock.getAssignments()).thenReturn(emptyList());

        Assignment resultAssignment = defaultAssignmentService.getAssignmentById(ID);

        assertEquals(assignmentMock, resultAssignment);
    }
}
