package com.car.park.service.impl;

import com.car.park.entities.Route;
import com.car.park.repository.RouteRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultRouteServiceTest {

    private static final long ROUTE_ID = 2;
    private static final Integer LENGTH = 3;
    private static final String NUMBER = "345";
    private static final String DESCRIPTION_EN = "DESCRIPTION_EN";
    private static final String DESCRIPTION_UA = "DESCRIPTION_UA";

    @InjectMocks
    DefaultRouteService defaultRouteService;
    @Mock
    private RouteRepository routeRepository;

    @Mock
    private Route route;

    @Test
    public void shouldCreateNewRoute() {
        defaultRouteService.createNewRoute(route);

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

        defaultRouteService.updateRoute(route);

        verify(originalRoute).setNumber(NUMBER);
        verify(originalRoute).setLength(LENGTH);
        verify(originalRoute).setDescriptionEn(DESCRIPTION_EN);
        verify(originalRoute).setDescriptionUa(DESCRIPTION_UA);
        verify(routeRepository).update(originalRoute);
    }


}