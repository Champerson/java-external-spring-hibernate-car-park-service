package com.car.park.repository;

import com.car.park.entities.Bus;

import java.util.List;

public interface BusRepository {

    void create(Bus bus);

    Bus read(Long busId);

    Bus read(String number);

    List<Bus> readAll();

    void update(Bus bus);

    void delete(Long busId);
}
