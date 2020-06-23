package com.car.park.service;

import com.car.park.entities.User;
import com.car.park.entities.UserRole;
import com.car.park.entities.dtos.UserDto;

import java.util.List;

/**
 * Interacts with part of repository's layer which is responsible for user.
 * @see User
 * @see UserDto
 * @see UserRole
 */
public interface UserService {


    /**
     * Create new user by UserDto instance
     * @param userDto UserDto instance
     */
    void registerNewUser(UserDto userDto);

    /**
     * Delete user bu user id
     * @param userId user id
     */
    void deleteUser(Long userId);

    /**
     * Update user info by UserDto instance
     * @param userDto UserDto instance
     */
    void updateUser(UserDto userDto);

    /**
     * Update user password by UserDto instance
     * @param userDto UserDto instance
     */
    void updateUserPassword(UserDto userDto);

    /**
     * Update user role
     * @param userId user id
     * @param userRole role to change
     * @return updated user
     */
    User updateUserAccessRole(Long userId, UserRole userRole);

    /**
     * Find user by given login
     * @param login user login
     * @return user
     */
    User getUserByLogin(String login);

    /**
     * Get all users
     * @return list of users
     */
    List<User> getAllUsers();

    /**
     * Get drivers that ara available for assignment
     * @return list of available drivers
     */
    List<User> getDriversAvailableForAssignment();

    /**
     * Get user by id
     * @param userId user id
     * @return user
     */
    User getUserById(Long userId);
}
