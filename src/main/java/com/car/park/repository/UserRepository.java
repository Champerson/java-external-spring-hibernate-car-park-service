package com.car.park.repository;

import com.car.park.entities.User;

import java.util.List;

public interface UserRepository {

    void create(User user);

    User read(Long userId);

    User read(String userLogin);

    List<User> readAll();

    void update(User user);

    void delete(Long userId);
}
