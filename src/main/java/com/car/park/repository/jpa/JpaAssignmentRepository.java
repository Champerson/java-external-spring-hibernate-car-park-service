package com.car.park.repository.jpa;

import com.car.park.repository.AssignmentRepository;
import com.car.park.entities.Assignment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaAssignmentRepository implements AssignmentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void create(Assignment assignment) {
        entityManager.merge(assignment);
        entityManager.flush();
    }

    @Override
    @Transactional(readOnly = true)
    public Assignment read(Long assignmentId) {
        return entityManager.find(Assignment.class, assignmentId);
    }

    @Override
    @Transactional(readOnly = true)
    public Assignment readByBusId(Long busId) {
        return entityManager.createNamedQuery("Assignment.readByBusId", Assignment.class)
                .setParameter("busId", busId)
                .getResultList().stream().findFirst().orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Assignment readByDriverId(Long driverId) {
        return entityManager.createNamedQuery("Assignment.readByDriverId", Assignment.class)
                .setParameter("driverId", driverId)
                .getResultList().stream().findFirst().orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Assignment> readByRouteId(Long routeId) {
        return entityManager.createNamedQuery("Assignment.readByRouteId", Assignment.class)
                .setParameter("routeId", routeId)
                .getResultList();
    }

    @Override
    @Transactional
    public void update(Assignment assignment) {
        entityManager.merge(assignment);
    }

    @Override
    @Transactional
    public void delete(Long assignmentId) {
        Assignment assignment = entityManager.find(Assignment.class, assignmentId);
        entityManager.remove(assignment);
    }
}
