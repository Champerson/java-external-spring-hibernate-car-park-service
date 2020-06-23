package com.car.park.service;

import com.car.park.entities.Route;
import com.car.park.entities.dtos.RouteDto;

import java.util.List;

/**
 * Interacts with part of repository's layer which is responsible for route.
 * @see Route
 * @see RouteDto
 */
public interface RouteService {

    /**
     * Create new route by RouteDto instance
     * @param routeDto RouteDto instance
     */
    void createNewRoute(RouteDto routeDto);

    /**
     * Update route by RouteDto instance
     * @param routeDto RouteDto instance
     */
    void updateRoute(RouteDto routeDto);

    /**
     * Get route by given id
     * @param routeId given route id
     * @return route
     */
    Route getRouteById(Long routeId);

    /**
     * Find route by given number
     * @param routeNumber given route number
     * @return route
     */
    Route getRouteByNumber(String routeNumber);

    /**
     * Get all routes
     * @return list of all routes
     */
    List<Route> getAllRoutes();

    /**
     * Delete route by given id
     * @param routeId route id
     */
    void deleteRoute(Long routeId);
}
