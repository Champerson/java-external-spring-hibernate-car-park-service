package com.car.park.service.impl;

import com.car.park.entities.Assignment;
import com.car.park.entities.User;
import com.car.park.entities.dtos.UserDto;
import com.car.park.repository.AssignmentRepository;
import com.car.park.repository.UserRepository;
import com.car.park.web.support.PasswordEncoder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static com.car.park.entities.UserRole.ROLE_ADMIN;
import static com.car.park.entities.UserRole.ROLE_DRIVER;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultUserServiceTest {


    private static final int AGE = 10;
    private static final long USER_ID = 2;
    private static final long ASSIGNMENT_ID = 2;
    private static final String NAME = "name";
    private static final String LOGIN = "login";
    private static final String EMAIL = "email";
    private static final String PHONE = "12353124";
    private static final String USER_PASSWORD = "password";
    private static final String USER_PASSWORD_ENCODED = "encodedPassword";

    @InjectMocks
    private DefaultUserService defaultUserService;
    @Mock
    private UserRepository userRepositoryMock;
    @Mock
    private AssignmentRepository assignmentRepositoryMock;
    @Mock
    private PasswordEncoder passwordEncoderMock;

    @Mock
    private User userMock;
    @Mock
    private UserDto userDtoMock;
    @Mock
    private List<User> usersMock;

    @Test
    public void shouldRegisterNewUser() {
        defaultUserService.registerNewUser(userDtoMock);

        verify(userRepositoryMock).create(any(User.class));
    }

    @Test
    public void shouldDeleteUserAndAssignmentWhenAssignmentNotNull() {
        Assignment userAssignment = mock(Assignment.class);
        when(assignmentRepositoryMock.readByDriverId(USER_ID)).thenReturn(userAssignment);
        when(userAssignment.getId()).thenReturn(ASSIGNMENT_ID);

        defaultUserService.deleteUser(USER_ID);

        verify(assignmentRepositoryMock).delete(userAssignment.getId());
        verify(userRepositoryMock).delete(USER_ID);
    }

    @Test
    public void shouldDeleteUserWithoutAssignmentWhenAssignmentIsNull() {
        defaultUserService.deleteUser(USER_ID);

        verify(userRepositoryMock).delete(USER_ID);
    }

    @Test
    public void shouldUpdateUser() {
        User originalUser = mock(User.class);
        UserDto userDto = new UserDto();
        userDto.setId(USER_ID);
        userDto.setAge(String.valueOf(AGE));
        userDto.setName(NAME);
        userDto.setEmail(EMAIL);
        userDto.setPhone(PHONE);

        when(userRepositoryMock.read(USER_ID)).thenReturn(originalUser);

        defaultUserService.updateUser(userDto);

        verify(originalUser).setAge(AGE);
        verify(originalUser).setEmail(EMAIL);
        verify(originalUser).setName(NAME);
        verify(originalUser).setPhone(PHONE);
        verify(userRepositoryMock).update(originalUser);
    }

    @Test
    public void shouldUpdateUserAccessRole() {
        when(userRepositoryMock.read(USER_ID)).thenReturn(userMock);

        User resultUser = defaultUserService.updateUserAccessRole(USER_ID, ROLE_ADMIN);

        verify(userMock).setAccessRole(ROLE_ADMIN);
        verify(userRepositoryMock).update(userMock);

        assertEquals(userMock, resultUser);
    }

    @Test
    public void shouldReturnUserByLogin() {
        when(userRepositoryMock.read(LOGIN)).thenReturn(userMock);

        User resultUser = defaultUserService.getUserByLogin(LOGIN);

        assertEquals(userMock, resultUser);
    }

    @Test
    public void shouldReturnListOfAllUsers() {
        when(userRepositoryMock.readAll()).thenReturn(usersMock);

        List<User> resultList = defaultUserService.getAllUsers();

        assertEquals(usersMock, resultList);
    }

    @Test
    public void shouldReturnListOfAvailableForAssignmentDriversByAssignment() {
        List<User> allDrivers = new ArrayList<>();
        User driverWithAssignment = mock(User.class);
        User driverWithoutAssignment = mock(User.class);
        Assignment assignment = mock(Assignment.class);

        allDrivers.add(driverWithAssignment);
        allDrivers.add(driverWithoutAssignment);

        when(userRepositoryMock.readAll()).thenReturn(allDrivers);
        when(driverWithAssignment.getAccessRole()).thenReturn(ROLE_DRIVER);
        when(driverWithoutAssignment.getAccessRole()).thenReturn(ROLE_DRIVER);
        when(driverWithAssignment.getAssignment()).thenReturn(assignment);
        when(driverWithoutAssignment.getAssignment()).thenReturn(null);

        List<User> availableDrivers = defaultUserService.getDriversAvailableForAssignment();

        assertEquals(driverWithoutAssignment, availableDrivers.get(0));
    }

    @Test
    public void shouldReturnListOfAvailableForAssignmentDriversByRole() {
        List<User> allDrivers = new ArrayList<>();
        User driver = mock(User.class);
        User admin = mock(User.class);

        allDrivers.add(driver);
        allDrivers.add(admin);

        when(userRepositoryMock.readAll()).thenReturn(allDrivers);
        when(admin.getAccessRole()).thenReturn(ROLE_ADMIN);
        when(driver.getAccessRole()).thenReturn(ROLE_DRIVER);
        when(admin.getAssignment()).thenReturn(null);
        when(driver.getAssignment()).thenReturn(null);

        List<User> availableDrivers = defaultUserService.getDriversAvailableForAssignment();

        assertEquals(driver, availableDrivers.get(0));
    }

    @Test
    public void shouldReturnUserById() {
        when(userRepositoryMock.read(USER_ID)).thenReturn(userMock);

        User resultUser = defaultUserService.getUserById(USER_ID);

        assertEquals(userMock, resultUser);
    }
}