package com.car.park.service;

import com.car.park.entities.Bus;
import com.car.park.entities.dtos.BusDto;

import java.util.List;

/**
 * Interacts with part of repository's layer which is responsible for bus.
 * @see Bus
 * @see BusDto
 */
public interface BusService {

    /**
     * Create new bus by BusDto instance
     * @param busDto BusDto instance
     */
    void createNewBus(BusDto busDto);

    /**
     * Update bus by BusDto instance
     * @param busDto BusDto instance
     */
    void updateBus(BusDto busDto);

    /**
     * Delete bus by given bus id
     * @param busId bus id
     */
    void deleteBus(Long busId);

    /**
     * Get bus by given bus id
     * @param busId bus id
     * @return bus
     */
    Bus getBusById(Long busId);

    /**
     * Find bus by given bus number
     * @param busNumber
     * @return
     */
    Bus getBusByNumber(String busNumber);

    /**
     * Get all buses
     * @return list of all buses
     */
    List<Bus> getAllBuses();

    /**
     * Get all buses available for assignment
     * @return list of available buses
     */
    List<Bus> getBusesAvailableForAssignment();
}
