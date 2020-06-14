package com.car.park.service.simple;

import com.car.park.entities.Assignment;
import com.car.park.entities.User;
import com.car.park.entities.UserRole;
import com.car.park.repository.AssignmentRepository;
import com.car.park.repository.UserRepository;
import com.car.park.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.time.LocalDateTime.now;
import static java.util.stream.Collectors.toList;

@Service
public class DefaultUserService implements UserService {

    private UserRepository userRepository;
    private AssignmentRepository assignmentRepository;

    @Override
    @Transactional
    public void registerNewUser(User user) {
        user.setAccessRole(UserRole.ROLE_DRIVER);
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
    public void updateUser(User modifiedUser) {
        User originalUser = userRepository.read(modifiedUser.getId());
        originalUser.setAge(modifiedUser.getAge());
        originalUser.setEmail(modifiedUser.getEmail());
        originalUser.setName(modifiedUser.getName());
        originalUser.setPhone(modifiedUser.getPhone());
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
                .filter(user -> UserRole.ROLE_DRIVER.equals(user.getAccessRole()) && user.getAssignment() == null)
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
}
