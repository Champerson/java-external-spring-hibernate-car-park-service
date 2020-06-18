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
    private UserRepository userRepository;
    @Mock
    private AssignmentRepository assignmentRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private User user;
    @Mock
    private UserDto userDto;
    @Mock
    private List<User> users;

    @Test
    public void shouldRegisterNewUser() {
        when(user.getPassword()).thenReturn(USER_PASSWORD);
        when(passwordEncoder.encode(USER_PASSWORD)).thenReturn(USER_PASSWORD_ENCODED);

        defaultUserService.registerNewUser(userDto);

        verify(user).setPassword(USER_PASSWORD_ENCODED);
        verify(user).setAccessRole(ROLE_DRIVER);
        verify(user).setCreationTime(any());
        verify(userRepository).create(user);
    }

    @Test
    public void shouldDeleteUserAndAssignmentWhenAssignmentNotNull() {
        Assignment userAssignment = mock(Assignment.class);
        when(assignmentRepository.readByDriverId(USER_ID)).thenReturn(userAssignment);
        when(userAssignment.getId()).thenReturn(ASSIGNMENT_ID);

        defaultUserService.deleteUser(USER_ID);

        verify(assignmentRepository).delete(userAssignment.getId());
        verify(userRepository).delete(USER_ID);
    }

    @Test
    public void shouldDeleteUserWithoutAssignmentWhenAssignmentIsNull() {
        defaultUserService.deleteUser(USER_ID);

        verify(userRepository).delete(USER_ID);
    }

    @Test
    public void shouldUpdateUser() {
        User originalUser = mock(User.class);
        when(user.getId()).thenReturn(USER_ID);
        when(userRepository.read(USER_ID)).thenReturn(originalUser);
        when(user.getAge()).thenReturn(AGE);
        when(user.getEmail()).thenReturn(EMAIL);
        when(user.getName()).thenReturn(NAME);
        when(user.getPhone()).thenReturn(PHONE);

        defaultUserService.updateUser(userDto);

        verify(originalUser).setAge(AGE);
        verify(originalUser).setEmail(EMAIL);
        verify(originalUser).setName(NAME);
        verify(originalUser).setPhone(PHONE);
        verify(userRepository).update(originalUser);
    }

    @Test
    public void shouldUpdateUserAccessRole() {
        when(userRepository.read(USER_ID)).thenReturn(user);

        User resultUser = defaultUserService.updateUserAccessRole(USER_ID, ROLE_ADMIN);

        verify(user).setAccessRole(ROLE_ADMIN);
        verify(userRepository).update(user);

        assertEquals(user, resultUser);
    }

    @Test
    public void shouldReturnUserByLogin() {
        when(userRepository.read(LOGIN)).thenReturn(user);

        User resultUser = defaultUserService.getUserByLogin(LOGIN);

        assertEquals(user, resultUser);
    }

    @Test
    public void shouldReturnListOfAllUsers() {
        when(userRepository.readAll()).thenReturn(users);

        List<User> resultList = defaultUserService.getAllUsers();

        assertEquals(users, resultList);
    }

    @Test
    public void shouldReturnListOfAvailableForAssignmentDriversByAssignment() {
        List<User> allDrivers = new ArrayList<>();
        User driverWithAssignment = mock(User.class);
        User driverWithoutAssignment = mock(User.class);
        Assignment assignment = mock(Assignment.class);

        allDrivers.add(driverWithAssignment);
        allDrivers.add(driverWithoutAssignment);

        when(userRepository.readAll()).thenReturn(allDrivers);
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

        when(userRepository.readAll()).thenReturn(allDrivers);
        when(admin.getAccessRole()).thenReturn(ROLE_ADMIN);
        when(driver.getAccessRole()).thenReturn(ROLE_DRIVER);
        when(admin.getAssignment()).thenReturn(null);
        when(driver.getAssignment()).thenReturn(null);

        List<User> availableDrivers = defaultUserService.getDriversAvailableForAssignment();

        assertEquals(driver, availableDrivers.get(0));
    }

    @Test
    public void shouldReturnUserById() {
        when(userRepository.read(USER_ID)).thenReturn(user);

        User resultUser = defaultUserService.getUserById(USER_ID);

        assertEquals(user, resultUser);
    }
}