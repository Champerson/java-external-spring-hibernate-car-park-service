package com.car.park.service;

import com.car.park.entities.User;
import com.car.park.entities.UserRole;
import com.car.park.entities.dtos.UserDto;

import java.util.List;

public interface UserService {

    void registerNewUser(UserDto userDto);

    void deleteUser(Long userId);

    void updateUser(UserDto userDto);

    void updateUserPassword(UserDto userDto);

    User updateUserAccessRole(Long userId, UserRole userRole);

    User getUserByLogin(String login);

    List<User> getAllUsers();

    List<User> getDriversAvailableForAssignment();

    User getUserById(Long userId);
}
