package com.car.park.repository;

import com.car.park.entities.Route;

import java.util.List;

public interface RouteRepository {

    void create(Route route);

    Route read(Long routeId);

    Route read(String number);

    List<Route> readAll();

    void update(Route route);

    void delete(Long routeId);
}
