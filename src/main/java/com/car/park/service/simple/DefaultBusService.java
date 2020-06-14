package com.car.park.service.simple;

import com.car.park.entities.Bus;
import com.car.park.repository.AssignmentRepository;
import com.car.park.repository.BusRepository;
import com.car.park.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.time.LocalDateTime.now;
import static java.util.stream.Collectors.toList;

@Service
public class DefaultBusService implements BusService {

    private BusRepository busRepository;
    private AssignmentRepository assignmentRepository;

    @Override
    @Transactional
    public void createNewBus(Bus bus) {
        bus.setCreationTime(now());
        busRepository.create(bus);
    }

    @Override
    @Transactional
    public void updateBus(Bus modifiedBus) {
        Bus originalBus = busRepository.read(modifiedBus.getId());
        originalBus.setMileage(modifiedBus.getMileage());
        originalBus.setModel(modifiedBus.getModel());
        originalBus.setNumber(modifiedBus.getNumber());
        originalBus.setPassengersCapacity(modifiedBus.getPassengersCapacity());
        originalBus.setColourEn(modifiedBus.getColourEn());
        originalBus.setColourUa(modifiedBus.getColourUa());
        originalBus.setNotesEn(modifiedBus.getNotesEn());
        originalBus.setNotesUa(modifiedBus.getNotesUa());
        busRepository.update(originalBus);
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
