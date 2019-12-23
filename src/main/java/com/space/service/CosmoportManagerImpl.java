package com.space.service;

import com.space.model.*;
import com.space.repository.CosmoportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CosmoportManagerImpl implements CosmoportManager {
    private CosmoportRepository cosmoportRepository;
    @Autowired
    public void setCosmoportRepository(CosmoportRepository cosmoportRepository) {
        this.cosmoportRepository = cosmoportRepository;
    }

    @Override
    public List<Ship> getShips(SelectListShipDetails shipDetails) {
        return cosmoportRepository.getShips(shipDetails);
    }

    @Override
    public Integer getCount(SelectCountShipDetails shipDetails) {
        return cosmoportRepository.getCount(shipDetails);
    }

    @Override
    public Ship getShip(IdShipDetails shipDetails) {
        return cosmoportRepository.getShip(shipDetails);
    }

    @Override
    public Ship addShip(AddShipDetails shipDetails) {
        return cosmoportRepository.addShip(shipDetails);
    }

    @Override
    public Ship editShip(EditShipDetails shipDetails) {
        return cosmoportRepository.editShip(shipDetails);
    }

    @Override
    public Integer deleteShip(IdShipDetails shipDetails) {
        return cosmoportRepository.deleteShip(shipDetails);
    }
}
