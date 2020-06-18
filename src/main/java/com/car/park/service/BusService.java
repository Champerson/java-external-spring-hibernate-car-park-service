package com.car.park.service;

import com.car.park.entities.Bus;
import com.car.park.entities.dtos.BusDto;

import java.util.List;

public interface BusService {

    void createNewBus(BusDto busDto);

    void updateBus(BusDto busDto);

    void deleteBus(Long busId);

    Bus getBusById(Long busId);

    Bus getBusByNumber(String busNumber);

    List<Bus> getAllBuses();

    List<Bus> getBusesAvailableForAssignment();
}
