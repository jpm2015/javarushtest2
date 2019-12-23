package com.space.controller;

import com.space.model.*;
import com.space.service.CosmoportManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class CosmoportController {
    private CosmoportManager cosmoportManager;
    @Autowired
    public void setCosmoportManager(CosmoportManager cosmoportManager) {
        this.cosmoportManager = cosmoportManager;
    }

    @RequestMapping(value = "/rest/ships", method = RequestMethod.GET)
    @ResponseBody
    public List<Ship> getShips(@ModelAttribute SelectListShipDetails shipDetails, HttpServletResponse response) {
        List<Ship> list = null;
        if (shipDetails.validate() && ((list = cosmoportManager.getShips(shipDetails)) != null)) {
            response.setStatus(200);
            return list;
        } else {
            response.setStatus(400);
            return null;
        }
    }

    @RequestMapping(value = "/rest/ships/count", method = RequestMethod.GET)
    @ResponseBody
    public Integer getShipsCount(@ModelAttribute SelectCountShipDetails shipDetails, HttpServletResponse response) {
        Integer count = null;
        if (shipDetails.validate() && ((count = cosmoportManager.getCount(shipDetails)) != null)) {
            response.setStatus(200);
            return count;
        } else {
            response.setStatus(400);
            return null;
        }
    }

    @RequestMapping(value = "/rest/ships/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Ship getShip(@PathVariable("id") String id, HttpServletResponse response) {
        Ship ship = null;
        IdShipDetails shipDetails = new IdShipDetails();
        shipDetails.setId(id);
        if (shipDetails.validate()) {
            if ((ship = cosmoportManager.getShip(shipDetails)) != null) {
                response.setStatus(200);
                return ship;
            } else {
                response.setStatus(404);
                return null;
            }
        } else {
            response.setStatus(400);
            return null;
        }
    }

    @RequestMapping(value = "/rest/ships/", method = RequestMethod.POST)
    @ResponseBody
    public Ship addShip(@RequestBody AddShipDetails shipDetails, HttpServletResponse response) {
        Ship ship = null;
        if (shipDetails.validate() && ((ship = cosmoportManager.addShip(shipDetails)) != null)) {
            response.setStatus(200);
            return ship;
        } else {
            response.setStatus(400);
            return null;
        }
    }

    @RequestMapping(value = "/rest/ships/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Ship editShip(@RequestBody EditShipDetails shipDetails, @PathVariable("id") String id, HttpServletResponse response) {
        Ship ship = null;
        shipDetails.setId(id);
        if (shipDetails.validate()) {
            if ((ship = cosmoportManager.editShip(shipDetails)) != null) {
                response.setStatus(200);
                return ship;
            } else {
                response.setStatus(404);
                return null;
            }
        } else {
            response.setStatus(400);
            return null;
        }
    }

    @RequestMapping(value = "/rest/ships/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteShip(@PathVariable("id") String id, HttpServletResponse response) {
        Integer count = null;
        IdShipDetails shipDetails = new IdShipDetails();
        shipDetails.setId(id);
        if (shipDetails.validate()) {
            if ((count = cosmoportManager.deleteShip(shipDetails)) == 1) {
                response.setStatus(200);
            } else {
                response.setStatus(404);
            }
        } else {
            response.setStatus(400);
        }
    }
}
