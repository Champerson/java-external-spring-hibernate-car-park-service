package com.car.park.service.simple;

import com.car.park.entities.Route;
import com.car.park.repository.RouteRepository;
import com.car.park.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.time.LocalDateTime.now;

@Service
public class DefaultRouteService implements RouteService {

    private RouteRepository routeRepository;

    @Override
    @Transactional
    public void createNewRoute(Route route) {
        route.setCreationTime(now());
        routeRepository.create(route);
    }

    @Override
    @Transactional
    public void updateRoute(Route modifiedRoute) {
        Route originalRoute = routeRepository.read(modifiedRoute.getId());
        originalRoute.setNumber(modifiedRoute.getNumber());
        originalRoute.setLength(modifiedRoute.getLength());
        originalRoute.setDescriptionEn(modifiedRoute.getDescriptionEn());
        originalRoute.setDescriptionUa(modifiedRoute.getDescriptionUa());
        routeRepository.update(originalRoute);
    }

    @Override
    @Transactional(readOnly = true)
    public Route getRouteById(Long routeId) {
        Route route = routeRepository.read(routeId);
        route.getAssignments().iterator();
        return route;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Route> getAllRoutes() {
        return routeRepository.readAll();
    }

    @Override
    @Transactional
    public void deleteRoute(Long routeId) {
        routeRepository.delete(routeId);
    }

    @Autowired
    public void setRouteRepository(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }
}
