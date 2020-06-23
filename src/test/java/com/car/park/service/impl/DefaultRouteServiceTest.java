package com.car.park.service.impl;

import com.car.park.entities.Assignment;
import com.car.park.entities.Route;
import com.car.park.entities.dtos.RouteDto;
import com.car.park.repository.RouteRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultRouteServiceTest {

    private static final long ROUTE_ID = 2;
    private static final String LENGTH = "3";
    private static final String NUMBER = "345";
    private static final String DESCRIPTION_EN = "DESCRIPTION_EN";
    private static final String DESCRIPTION_UA = "DESCRIPTION_UA";

    @InjectMocks
    DefaultRouteService defaultRouteService;
    @Mock
    private RouteRepository routeRepositoryMock;

    @Mock
    private Route routeMock;
    @Mock
    private RouteDto routeDtoMock;

    @Test
    public void shouldCreateNewRoute() {
        defaultRouteService.createNewRoute(routeDtoMock);

        verify(routeRepositoryMock).create(any(Route.class));
    }

    @Test
    public void shouldUpdateRoute() {
        RouteDto routeDto = new RouteDto();

        routeDto.setId(ROUTE_ID);
        routeDto.setNumber(NUMBER);
        routeDto.setLength(LENGTH);
        routeDto.setDescriptionEn(DESCRIPTION_EN);
        routeDto.setDescriptionUa(DESCRIPTION_UA);

        when(routeRepositoryMock.read(ROUTE_ID)).thenReturn(routeMock);

        defaultRouteService.updateRoute(routeDto);

        verify(routeRepositoryMock).update(routeMock);
    }

    @Test
    public void shouldReturnRouteById() {
        Assignment assignment = mock(Assignment.class);
        routeMock.setAssignments(new ArrayList<>());
        routeMock.getAssignments().add(assignment);

        when(routeRepositoryMock.read(ROUTE_ID)).thenReturn(routeMock);

        Route resultRoute = defaultRouteService.getRouteById(ROUTE_ID);

        assertEquals(routeMock, resultRoute);
    }

    @Test
    public void shouldReturnRouteByNumber() {
        List<Assignment> assignments = new ArrayList<>();
        assignments.add(new Assignment());

        when(routeRepositoryMock.read(NUMBER)).thenReturn(routeMock);
        when(routeMock.getAssignments()).thenReturn(assignments);

        Route resultRoute = defaultRouteService.getRouteByNumber(NUMBER);

        assertEquals(routeMock, resultRoute);
    }

    @Test
    public void shouldReturnAllRoutes() {
        List<Route> routes = new ArrayList<>();
        routes.add(routeMock);

        when(routeRepositoryMock.readAll()).thenReturn(routes);

        List<Route> resultRoutes = defaultRouteService.getAllRoutes();

        assertEquals(routes, resultRoutes);
    }

    @Test
    public void shouldDeleteRouteById() {
        defaultRouteService.deleteRoute(ROUTE_ID);

        verify(routeRepositoryMock).delete(ROUTE_ID);
    }
}