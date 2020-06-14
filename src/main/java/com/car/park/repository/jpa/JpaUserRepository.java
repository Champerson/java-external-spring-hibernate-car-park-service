package com.car.park.repository.jpa;

import com.car.park.entities.User;
import com.car.park.repository.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaUserRepository implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void create(User user) {
        entityManager.merge(user);
        entityManager.flush();
    }

    @Override
    @Transactional(readOnly = true)
    public User read(Long userId) {
        return entityManager.find(User.class, userId);
    }

    @Override
    @Transactional(readOnly = true)
    public User read(String userLogin) {
        return entityManager
                .createNamedQuery("User.readByLogin", User.class)
                .setParameter("login", userLogin)
                .getResultList().stream().findFirst().orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> readAll() {
        return entityManager.createNamedQuery("User.readAll", User.class).getResultList();
    }

    @Override
    @Transactional
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    @Transactional
    public void delete(Long userId) {
        User user = entityManager.find(User.class, userId);
        entityManager.remove(user);
    }
}
