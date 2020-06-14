package com.car.park.repository.jpa;

import com.car.park.repository.BusRepository;
import com.car.park.entities.Bus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaBusRepository implements BusRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void create(Bus bus) {
        entityManager.merge(bus);
        entityManager.flush();
    }

    @Override
    @Transactional(readOnly = true)
    public Bus read(Long busId) {
        return entityManager.find(Bus.class, busId);
    }

    @Override
    @Transactional(readOnly = true)
    public Bus read(String number) {
        return entityManager.createNamedQuery("Bus.readByNumber", Bus.class)
                .setParameter("number", number)
                .getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Bus> readAll() {
        return entityManager.createNamedQuery("Bus.readAll", Bus.class).getResultList();
    }

    @Override
    @Transactional
    public void update(Bus bus) {
        entityManager.merge(bus);
    }

    @Override
    @Transactional
    public void delete(Long busId) {
        Bus bus = entityManager.find(Bus.class, busId);
        entityManager.remove(bus);
    }
}
