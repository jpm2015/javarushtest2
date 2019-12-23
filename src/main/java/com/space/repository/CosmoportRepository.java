package com.space.repository;

import com.space.model.*;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CosmoportRepository extends CrudRepository<Ship, Long> {
    List<Ship> getShips(SelectListShipDetails shipDetails);
    Integer getCount(SelectCountShipDetails shipDetails);
    Ship getShip(IdShipDetails shipDetails);
    Integer deleteShip(IdShipDetails shipDetails);
    Ship addShip(AddShipDetails shipDetails);
    Ship editShip(EditShipDetails shipDetails);
}
