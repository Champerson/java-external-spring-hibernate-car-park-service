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

import static java.time.LocalDateTime.now;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultBusServiceTest {

    private static final long BUS_ID = 3;
    private static final String NUMBER = "AA2354AA";
    private static final String MODEL = "bmw";
    private static final String MILEAGE = "10";
    private static final String PASSENGER_CAPACITY = "15";
    private static final String COLOUR_EN = "colour";
    private static final String COLOUR_UA = "колір";
    private static final String NOTES_EN = "notes";
    private static final String NOTES_UA = "нотатки";

    @InjectMocks
    DefaultBusService defaultBusService;
    @Mock
    private BusRepository busRepositoryMock;
    @Mock
    private AssignmentRepository assignmentRepositoryMock;

    @Mock
    Bus busMock;
    @Mock
    BusDto busDtoMock;

    @Test
    public void shouldCreateNewBus() {
        defaultBusService.createNewBus(busDtoMock);

        verify(busRepositoryMock).create(any(Bus.class));
    }

    @Test
    public void shouldUpdateBus() {
        BusDto busDto = new BusDto();

        busDto.setId(BUS_ID);
        busDto.setNumber(NUMBER);
        busDto.setMileage(MILEAGE);
        busDto.setPassengersCapacity(PASSENGER_CAPACITY);
        busDto.setModel(MODEL);
        busDto.setColourEn(COLOUR_EN);
        busDto.setColourUa(COLOUR_UA);
        busDto.setNotesEn(NOTES_EN);
        busDto.setNotesUa(NOTES_UA);

        when(busRepositoryMock.read(BUS_ID)).thenReturn(busMock);

        defaultBusService.updateBus(busDto);

        verify(busRepositoryMock).update(busMock);
    }

    @Test
    public void shouldDeleteBusAndAssignmentWhenAssignmentNotNull() {
        Assignment busAssignment = mock(Assignment.class);
        when(busRepositoryMock.read(BUS_ID)).thenReturn(busMock);
        when(busMock.getAssignment()).thenReturn(busAssignment);

        defaultBusService.deleteBus(BUS_ID);

        verify(assignmentRepositoryMock).delete(busAssignment.getId());
        verify(busRepositoryMock).delete(BUS_ID);
    }

    @Test
    public void shouldDeleteBusWithoutAssignmentWhenAssignmentIsNull() {
        when(busRepositoryMock.read(BUS_ID)).thenReturn(busMock);
        when(busMock.getAssignment()).thenReturn(null);

        defaultBusService.deleteBus(BUS_ID);

        verify(busRepositoryMock).delete(BUS_ID);
    }

    @Test
    public void shouldReturnBusById() {
        when(busRepositoryMock.read(BUS_ID)).thenReturn(busMock);

        Bus resultBus = defaultBusService.getBusById(BUS_ID);

        assertEquals(busMock, resultBus);
    }

    @Test
    public void shouldReturnBusByNumber() {
        when(busRepositoryMock.read(NUMBER)).thenReturn(busMock);

        Bus resultBus = defaultBusService.getBusByNumber(NUMBER);

        assertEquals(busMock, resultBus);
    }

    @Test
    public void shouldReturnAllBuses() {
        List<Bus> buses = new ArrayList<>();
        buses.add(busMock);

        when(busRepositoryMock.readAll()).thenReturn(buses);

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

        when(busRepositoryMock.readAll()).thenReturn(allBuses);
        when(busWithAssignment.getAssignment()).thenReturn(assignment);
        when(busWithoutAssignment.getAssignment()).thenReturn(null);

        List<Bus> availableBuses = defaultBusService.getBusesAvailableForAssignment();

        assertEquals(busWithoutAssignment, availableBuses.get(0));
    }
}