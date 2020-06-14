package com.car.park.repository.jpa;

import com.car.park.repository.RouteRepository;
import com.car.park.entities.Route;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaRouteRepository implements RouteRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void create(Route route) {
        entityManager.merge(route);
        entityManager.flush();
    }

    @Override
    @Transactional(readOnly = true)
    public Route read(Long routeId) {
        return entityManager.find(Route.class, routeId);
    }

    @Override
    @Transactional(readOnly = true)
    public Route read(String number) {
        return entityManager.createNamedQuery("Route.readByNumber", Route.class)
                .setParameter("number", number)
                .getResultList().stream().findFirst().orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Route> readAll() {
        return entityManager.createNamedQuery("Route.readAll", Route.class).getResultList();
    }

    @Override
    @Transactional
    public void update(Route route) {
        entityManager.merge(route);
    }

    @Override
    @Transactional
    public void delete(Long routeId) {
        Route route = entityManager.find(Route.class, routeId);
        entityManager.remove(route);
    }
}
