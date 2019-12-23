package com.space.repository;

import com.space.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;

@Repository
public class CosmoportRepositoryImpl implements CosmoportRepository {
    private EntityManager entityManager;
    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Ship> getShips(SelectListShipDetails shipDetails)  {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Ship> criteriaQuery = criteriaBuilder.createQuery(Ship.class);
            Root<Ship> fromRoot = criteriaQuery.from(Ship.class);
            criteriaQuery.select(fromRoot);

            String order = shipDetails.getOrder();
            if (order != null) criteriaQuery.orderBy(criteriaBuilder.asc(fromRoot.get(order)));

            List<javax.persistence.criteria.Predicate> filters = buildFilters(criteriaBuilder, fromRoot, shipDetails);
            criteriaQuery.where(filters.toArray(new javax.persistence.criteria.Predicate[0]));

            int limit = shipDetails.getPageSize();
            int offset = limit * shipDetails.getPageNumber();
            return entityManager.createQuery(criteriaQuery)
                    .setFirstResult(offset)
                    .setMaxResults(limit)
                    .getResultList();
        } catch (IllegalStateException | IllegalArgumentException | PersistenceException e ) {
            return null;
        }
    }

    @Override
    public Integer getCount(SelectCountShipDetails shipDetails) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
            Root<Ship> fromRoot = criteriaQuery.from(Ship.class);
            criteriaQuery.select(criteriaBuilder.count(fromRoot));

            List<javax.persistence.criteria.Predicate> filters = buildFilters(criteriaBuilder, fromRoot, shipDetails);
            criteriaQuery.where(filters.toArray(new javax.persistence.criteria.Predicate[0]));

            Long count = entityManager.createQuery(criteriaQuery).getSingleResult();
            return count.intValue();
        } catch (IllegalStateException | IllegalArgumentException | PersistenceException e ) {
            return null;
        }
    }

    @Override
    public Ship getShip(IdShipDetails shipDetails)  {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Ship> criteriaQuery = criteriaBuilder.createQuery(Ship.class);
            Root<Ship> fromRoot = criteriaQuery.from(Ship.class);
            criteriaQuery.select(fromRoot);

            criteriaQuery.where(criteriaBuilder.equal(fromRoot.get("id"), shipDetails.getId()));

            List<Ship> list = entityManager.createQuery(criteriaQuery).getResultList();
            return list.size() == 0 ? null : list.get(0);
        } catch (IllegalStateException | IllegalArgumentException | PersistenceException e ) {
            return null;
        }
    }

    @Transactional
    @Override
    public Ship addShip(AddShipDetails shipDetails)  {
        Ship ship = new Ship(shipDetails);
        try {
            try {
                entityManager.persist(ship);
                entityManager.flush();
                return ship;
            } catch (EntityExistsException e) {
                return null;
            }
        } catch (IllegalArgumentException | PersistenceException e ) {
            return null;
        }
    }

    @Transactional
    @Override
    public Ship editShip(EditShipDetails shipDetails)  {
        try {
            Ship ship = entityManager.find(Ship.class, shipDetails.getId());
            if (ship == null) return null;
            try {
                ship.syncWith(shipDetails);
                ship = entityManager.merge(ship);
                entityManager.flush();
                return ship;
            } catch (EntityExistsException e) {
                return null;
            }
        } catch (IllegalArgumentException | PersistenceException e ) {
            return null;
        }
    }

    @Transactional
    @Override
    public Integer deleteShip(IdShipDetails shipDetails)  {
        try {
            Ship ship = entityManager.find(Ship.class, shipDetails.getId());
            if (ship != null) {
                entityManager.remove(ship);
                return 1;
            } else return 0;
        } catch (IllegalArgumentException | PersistenceException e ) {
            return 0;
        }
    }

    @Override
    public <S extends Ship> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Ship> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Ship> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Ship> findAll() {
        return null;
    }

    @Override
    public Iterable<Ship> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Ship entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends Ship> entities) {

    }

    @Override
    public void deleteAll() {

    }

    private List<javax.persistence.criteria.Predicate> buildFilters(CriteriaBuilder criteriaBuilder, Root<?> fromRoot, SelectCountShipDetails shipDetails) {
        List<javax.persistence.criteria.Predicate> filters = new ArrayList<>();

        String name = shipDetails.getName();
        if (name != null) filters.add(criteriaBuilder.like(fromRoot.get("name"), "%" + name + "%"));
        String planet = shipDetails.getPlanet();
        if (planet != null) filters.add(criteriaBuilder.like(fromRoot.get("planet"), "%" + planet + "%"));
        String shipType = shipDetails.getShipType();
        if (shipType != null) filters.add(criteriaBuilder.equal(fromRoot.get("shipType"), shipType));
        Boolean isUsed = shipDetails.getIsUsed();
        if (isUsed != null) filters.add(criteriaBuilder.equal(fromRoot.get("isUsed"), isUsed));
        Date after = shipDetails.getAfter();
        if (after != null) filters.add(criteriaBuilder.greaterThanOrEqualTo(fromRoot.get("prodDate"), after));
        Date before = shipDetails.getBefore();
//        if (before != null) filters.add(criteriaBuilder.lessThanOrEqualTo(fromRoot.get("prodDate"), before));
        if (before != null) filters.add(criteriaBuilder.lessThan(fromRoot.get("prodDate"), before));
        Double minSpeed = shipDetails.getMinSpeed();
        if (minSpeed != null) filters.add(criteriaBuilder.greaterThanOrEqualTo(fromRoot.get("speed"), minSpeed));
        Double maxSpeed = shipDetails.getMaxSpeed();
        if (maxSpeed != null) filters.add(criteriaBuilder.lessThanOrEqualTo(fromRoot.get("speed"), maxSpeed));
        Integer minCrewSize = shipDetails.getMinCrewSize();
        if (minCrewSize != null) filters.add(criteriaBuilder.greaterThanOrEqualTo(fromRoot.get("crewSize"), minCrewSize));
        Integer maxCrewSize = shipDetails.getMaxCrewSize();
        if (maxCrewSize != null) filters.add(criteriaBuilder.lessThanOrEqualTo(fromRoot.get("crewSize"), maxCrewSize));
        Double minRating = shipDetails.getMinRating();
        if (minRating != null) filters.add(criteriaBuilder.greaterThanOrEqualTo(fromRoot.get("rating"), minRating));
        Double maxRating = shipDetails.getMaxRating();
        if (maxRating != null) filters.add(criteriaBuilder.lessThanOrEqualTo(fromRoot.get("rating"), maxRating));

        return filters;
    }
}
