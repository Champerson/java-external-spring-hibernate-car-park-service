package com.car.park.service;

import com.car.park.entities.Route;
import com.car.park.entities.dtos.RouteDto;

import java.util.List;

public interface RouteService {

    void createNewRoute(RouteDto routeDto);

    void updateRoute(RouteDto routeDto);

    Route getRouteById(Long routeId);

    Route getRouteByNumber(String routeNumber);

    List<Route> getAllRoutes();

    void deleteRoute(Long routeId);
}
