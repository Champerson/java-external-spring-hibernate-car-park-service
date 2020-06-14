package com.car.park.service;

import com.car.park.entities.Bus;

import java.util.List;

public interface BusService {

    void createNewBus(Bus bus);

    void updateBus(Bus bus);

    void deleteBus(Long busId);

    Bus getBusById(Long busId);

    List<Bus> getAllBuses();

    List<Bus> getBusesAvailableForAssignment();
}
