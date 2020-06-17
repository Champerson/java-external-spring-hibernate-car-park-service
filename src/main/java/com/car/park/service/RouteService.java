package com.car.park.service;

import com.car.park.entities.Route;

import java.util.List;

public interface RouteService {

    void createNewRoute(Route route);

    void updateRoute(Route route);

    Route getRouteById(Long routeId);

    Route getRouteByNumber(String routeNumber);

    List<Route> getAllRoutes();

    void deleteRoute(Long routeId);
}
