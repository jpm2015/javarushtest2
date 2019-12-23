package com.space.service;

import com.space.model.*;

import java.util.List;

public interface CosmoportManager {
    List<Ship> getShips(SelectListShipDetails shipDetails);
    Integer getCount(SelectCountShipDetails shipDetails);
    Ship getShip(IdShipDetails shipDetails);
    Integer deleteShip(IdShipDetails shipDetails);
    Ship addShip(AddShipDetails shipDetails);
    Ship editShip(EditShipDetails shipDetails);
}
