package com.car.park.service.impl;

import com.car.park.entities.Assignment;
import com.car.park.entities.Bus;
import com.car.park.entities.dtos.BusDto;
import com.car.park.repository.AssignmentRepository;
import com.car.park.repository.BusRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultBusServiceTest {

    private static final long BUS_ID = 3;
    private static final int MILEAGE= 10;
    private static final int PASSENGER_CAPACITY = 15;
    private static final String MODEL = "bmw";
    private static final String NUMBER = "AA2354AA";
    private static final String COLOUR_EN = "colour";
    private static final String COLOUR_UA = "колір";
    private static final String NOTES_EN = "notes";
    private static final String NOTES_UA = "нотатки";

    @InjectMocks
    DefaultBusService defaultBusService;
    @Mock
    private BusRepository busRepository;
    @Mock
    private AssignmentRepository assignmentRepository;

    @Mock
    Bus bus;
    @Mock
    BusDto busDto;

    @Test
    public void shouldCreateNewBus() {
        defaultBusService.createNewBus(busDto);

        verify(bus).setCreationTime(any());
        verify(busRepository).create(bus);
    }

    @Test
    public void shouldUpdateBus() {
        Bus originalBus = mock(Bus.class);
        when(bus.getId()).thenReturn(BUS_ID);
        when(busRepository.read(BUS_ID)).thenReturn(originalBus);
        when(bus.getMileage()).thenReturn(MILEAGE);
        when(bus.getModel()).thenReturn(MODEL);
        when(bus.getNumber()).thenReturn(NUMBER);
        when(bus.getPassengersCapacity()).thenReturn(PASSENGER_CAPACITY);
        when(bus.getColourEn()).thenReturn(COLOUR_EN);
        when(bus.getColourUa()).thenReturn(COLOUR_UA);
        when(bus.getNotesEn()).thenReturn(NOTES_EN);
        when(bus.getNotesUa()).thenReturn(NOTES_UA);

        defaultBusService.updateBus(busDto);

        verify(originalBus).setMileage(MILEAGE);
        verify(originalBus).setModel(MODEL);
        verify(originalBus).setPassengersCapacity(PASSENGER_CAPACITY);
        verify(originalBus).setNumber(NUMBER);
        verify(originalBus).setColourEn(COLOUR_EN);
        verify(originalBus).setColourUa(COLOUR_UA);
        verify(originalBus).setNotesEn(NOTES_EN);
        verify(originalBus).setNotesUa(NOTES_UA);
        verify(busRepository).update(originalBus);
    }

    @Test
    public void shouldDeleteBusAndAssignmentWhenAssignmentNotNull() {
        Assignment busAssignment = mock(Assignment.class);
        when(busRepository.read(BUS_ID)).thenReturn(bus);
        when(bus.getAssignment()).thenReturn(busAssignment);

        defaultBusService.deleteBus(BUS_ID);

        verify(assignmentRepository).delete(busAssignment.getId());
        verify(busRepository).delete(BUS_ID);
    }

    @Test
    public void shouldDeleteBusWithoutAssignmentWhenAssignmentIsNull() {
        when(busRepository.read(BUS_ID)).thenReturn(bus);
        when(bus.getAssignment()).thenReturn(null);

        defaultBusService.deleteBus(BUS_ID);

        verify(busRepository).delete(BUS_ID);
    }

    @Test
    public void shouldReturnBusById() {
        when(busRepository.read(BUS_ID)).thenReturn(bus);

        Bus resultBus = defaultBusService.getBusById(BUS_ID);

        assertEquals(bus, resultBus);
    }

    @Test
    public void shouldReturnBusByNumber() {
        when(busRepository.read(NUMBER)).thenReturn(bus);

        Bus resultBus = defaultBusService.getBusByNumber(NUMBER);

        assertEquals(bus, resultBus);
    }

    @Test
    public void shouldReturnAllBuses() {
        List<Bus> buses = new ArrayList<>();
        buses.add(bus);

        when(busRepository.readAll()).thenReturn(buses);

        List<Bus> resultBuses = defaultBusService.getAllBuses();

        assertEquals(buses, resultBuses);
    }

    @Test
    public void shouldReturnAllBusesAvailableForAssignment() {
        List<Bus> allBuses = new ArrayList<>();
        Bus busWithAssignment = mock(Bus.class);
        Bus busWithoutAssignment = mock(Bus.class);
        Assignment assignment = mock(Assignment.class);

        allBuses.add(busWithAssignment);
        allBuses.add(busWithoutAssignment);

        when(busRepository.readAll()).thenReturn(allBuses);
        when(busWithAssignment.getAssignment()).thenReturn(assignment);
        when(busWithoutAssignment.getAssignment()).thenReturn(null);

        List<Bus> availableBuses = defaultBusService.getBusesAvailableForAssignment();

        assertEquals(busWithoutAssignment, availableBuses.get(0));
    }
}