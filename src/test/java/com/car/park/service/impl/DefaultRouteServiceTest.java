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
    private static final int LENGTH = 3;
    private static final String NUMBER = "345";
    private static final String DESCRIPTION_EN = "DESCRIPTION_EN";
    private static final String DESCRIPTION_UA = "DESCRIPTION_UA";

    @InjectMocks
    DefaultRouteService defaultRouteService;
    @Mock
    private RouteRepository routeRepository;

    @Mock
    private Route route;
    @Mock
    private RouteDto routeDto;

    @Test
    public void shouldCreateNewRoute() {
        defaultRouteService.createNewRoute(routeDto);

        verify(route).setCreationTime(any());
        verify(routeRepository).create(route);
    }

    @Test
    public void shouldUpdateRoute() {
        Route originalRoute = mock(Route.class);
        when(route.getId()).thenReturn(ROUTE_ID);
        when(routeRepository.read(ROUTE_ID)).thenReturn(originalRoute);
        when(route.getNumber()).thenReturn(NUMBER);
        when(route.getLength()).thenReturn(LENGTH);
        when(route.getDescriptionEn()).thenReturn(DESCRIPTION_EN);
        when(route.getDescriptionUa()).thenReturn(DESCRIPTION_UA);

        defaultRouteService.updateRoute(routeDto);

        verify(originalRoute).setNumber(NUMBER);
        verify(originalRoute).setLength(LENGTH);
        verify(originalRoute).setDescriptionEn(DESCRIPTION_EN);
        verify(originalRoute).setDescriptionUa(DESCRIPTION_UA);
        verify(routeRepository).update(originalRoute);
    }

    @Test
    public void shouldReturnRouteById() {
        Assignment assignment = mock(Assignment.class);
        route.setAssignments(new ArrayList<>());
        route.getAssignments().add(assignment);

        when(routeRepository.read(ROUTE_ID)).thenReturn(route);

        Route resultRoute = defaultRouteService.getRouteById(ROUTE_ID);

        assertEquals(route, resultRoute);
    }

    @Test
    public void shouldReturnRouteByNumber() {
        List<Assignment> assignments = new ArrayList<>();
        assignments.add(new Assignment());

        when(routeRepository.read(NUMBER)).thenReturn(route);
        when(route.getAssignments()).thenReturn(assignments);

        Route resultRoute = defaultRouteService.getRouteByNumber(NUMBER);

        assertEquals(route, resultRoute);
    }

    @Test
    public void shouldReturnAllRoutes() {
        List<Route> routes = new ArrayList<>();
        routes.add(route);

        when(routeRepository.readAll()).thenReturn(routes);

        List<Route> resultRoutes = defaultRouteService.getAllRoutes();

        assertEquals(routes, resultRoutes);
    }

    @Test
    public void shouldDeleteRouteById() {
        defaultRouteService.deleteRoute(ROUTE_ID);

        verify(routeRepository).delete(ROUTE_ID);
    }
}