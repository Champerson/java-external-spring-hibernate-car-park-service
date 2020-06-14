package com.car.park.service;

import com.car.park.entities.User;
import com.car.park.entities.UserRole;

import java.util.List;

public interface UserService {

    void registerNewUser(User user);

    void deleteUser(Long userId);

    void updateUser(User modifiedUser);

    User updateUserAccessRole(Long userId, UserRole userRole);

    User getUserByLogin(String login);

    List<User> getAllUsers();

    List<User> getDriversAvailableForAssignment();

    User getUserById(Long userId);
}
