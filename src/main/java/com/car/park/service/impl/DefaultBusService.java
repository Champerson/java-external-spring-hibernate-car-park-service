package com.car.park.service.impl;

import com.car.park.entities.Bus;
import com.car.park.entities.dtos.BusDto;
import com.car.park.repository.AssignmentRepository;
import com.car.park.repository.BusRepository;
import com.car.park.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.lang.Integer.parseInt;
import static java.time.LocalDateTime.now;
import static java.util.stream.Collectors.toList;
import static org.springframework.util.StringUtils.isEmpty;

@Service
public class DefaultBusService implements BusService {

    private BusRepository busRepository;
    private AssignmentRepository assignmentRepository;

    @Override
    @Transactional
    public void createNewBus(BusDto busDto) {
        Bus bus = mapDtoToEntity(busDto, new Bus());
        bus.setCreationTime(now());
        busRepository.create(bus);
    }

    @Override
    @Transactional
    public void updateBus(BusDto busDto) {
        Bus originalBus = mapDtoToEntity(busDto, busRepository.read(busDto.getId()));
        busRepository.update(originalBus);
    }

    private Bus mapDtoToEntity(BusDto busDto, Bus bus) {
        bus.setNumber(busDto.getNumber());
        bus.setModel(busDto.getModel());
        bus.setMileage(isEmpty(busDto.getMileage()) ? null : parseInt(busDto.getMileage()));
        bus.setPassengersCapacity(isEmpty(busDto.getPassengersCapacity()) ? null : parseInt(busDto.getPassengersCapacity()));
        bus.setColourEn(busDto.getColourEn());
        bus.setColourUa(busDto.getColourUa());
        bus.setNotesEn(busDto.getNotesEn());
        bus.setNotesUa(busDto.getNotesUa());
        return bus;
    }

    @Override
    @Transactional
    public void deleteBus(Long busId) {
        Bus bus = busRepository.read(busId);
        if (bus.getAssignment() != null) {
            assignmentRepository.delete(bus.getAssignment().getId());
        }
        busRepository.delete(busId);
    }

    @Override
    @Transactional(readOnly = true)
    public Bus getBusById(Long busId) {
        return busRepository.read(busId);
    }

    @Override
    public Bus getBusByNumber(String busNumber) {
        return busRepository.read(busNumber);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Bus> getAllBuses() {
        return busRepository.readAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Bus> getBusesAvailableForAssignment() {
        return busRepository.readAll().stream()
                .filter(bus -> bus.getAssignment() == null)
                .collect(toList());
    }

    @Autowired
    public void setBusRepository(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    @Autowired
    public void setAssignmentRepository(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }
}
