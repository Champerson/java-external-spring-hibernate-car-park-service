package com.car.park.service.impl;

import com.car.park.entities.Assignment;
import com.car.park.entities.User;
import com.car.park.entities.UserRole;
import com.car.park.entities.dtos.UserDto;
import com.car.park.repository.AssignmentRepository;
import com.car.park.repository.UserRepository;
import com.car.park.service.UserService;
import com.car.park.web.support.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.car.park.entities.UserRole.ROLE_DRIVER;
import static java.lang.Integer.parseInt;
import static java.time.LocalDateTime.now;
import static java.util.stream.Collectors.toList;
import static org.springframework.util.StringUtils.isEmpty;

@Service
public class DefaultUserService implements UserService {

    private UserRepository userRepository;
    private AssignmentRepository assignmentRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void registerNewUser(UserDto userDto) {
        User user = new User();
        user.setLogin(userDto.getLogin());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setPhone(userDto.getPhone());
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setAge(isEmpty(userDto.getAge()) ? null : parseInt(userDto.getAge()));
        user.setAccessRole(ROLE_DRIVER);
        user.setCreationTime(now());
        userRepository.create(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        Assignment userAssignment = assignmentRepository.readByDriverId(userId);
        if (userAssignment != null) {
            assignmentRepository.delete(userAssignment.getId());
        }
        userRepository.delete(userId);
    }

    @Override
    @Transactional
    public void updateUser(UserDto userDto) {
        User originalUser = userRepository.read(userDto.getId());
        originalUser.setPhone(userDto.getPhone());
        originalUser.setEmail(userDto.getEmail());
        originalUser.setName(userDto.getName());
        originalUser.setAge(isEmpty(userDto.getAge()) ? null : parseInt(userDto.getAge()));
        userRepository.update(originalUser);
    }

    @Override
    public void updateUserPassword(UserDto userDto) {
        User originalUser = userRepository.read(userDto.getId());
        originalUser.setPassword(passwordEncoder.encode(userDto.getNewPassword()));
        userRepository.update(originalUser);
    }

    @Override
    @Transactional
    public User updateUserAccessRole(Long userId, UserRole userRole) {
        User user = userRepository.read(userId);
        user.setAccessRole(userRole);
        userRepository.update(user);
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByLogin(String login) {
        return userRepository.read(login);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.readAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getDriversAvailableForAssignment() {
        return userRepository.readAll().stream()
                .filter(user -> ROLE_DRIVER.equals(user.getAccessRole()) && user.getAssignment() == null)
                .collect(toList());
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long userId) {
        return userRepository.read(userId);
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setAssignmentRepository(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
