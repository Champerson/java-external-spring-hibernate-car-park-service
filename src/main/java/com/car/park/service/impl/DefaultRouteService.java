package com.car.park.service.impl;

import com.car.park.entities.Route;
import com.car.park.entities.dtos.RouteDto;
import com.car.park.repository.RouteRepository;
import com.car.park.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.lang.Integer.parseInt;
import static java.time.LocalDateTime.now;
import static org.springframework.util.StringUtils.isEmpty;

@Service
public class DefaultRouteService implements RouteService {

    private RouteRepository routeRepository;

    @Override
    @Transactional
    public void createNewRoute(RouteDto routeDto) {
        Route route = mapDtoToEntity(routeDto, new Route());
        route.setCreationTime(now());
        routeRepository.create(route);
    }

    @Override
    @Transactional
    public void updateRoute(RouteDto routeDto) {
        Route originalRoute = mapDtoToEntity(routeDto, routeRepository.read(routeDto.getId()));
        routeRepository.update(originalRoute);
    }

    private Route mapDtoToEntity(RouteDto routeDto, Route route) {
        route.setNumber(routeDto.getNumber());
        route.setLength(isEmpty(routeDto.getLength()) ? null : parseInt(routeDto.getLength()));
        route.setDescriptionEn(routeDto.getDescriptionEn());
        route.setDescriptionUa(routeDto.getDescriptionUa());
        return route;
    }

    @Override
    @Transactional(readOnly = true)
    public Route getRouteById(Long routeId) {
        Route route = routeRepository.read(routeId);
        route.getAssignments().iterator();
        return route;
    }

    @Override
    public Route getRouteByNumber(String routeNumber) {
        return routeRepository.read(routeNumber);
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
