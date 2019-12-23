package com.space.model;

public class EditShipDetails extends AddShipDetails {
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(String id) {
        this.id = convertId(id);
    }

    @Override
    public boolean validate() {
        boolean result = true;
        result &= longPositive.test(id);
        result &= stringLength1to50OrNull.test(name);
        result &= stringLength1to50OrNull.test(planet);
        result &= stringIsShipTypeOrNull.test(shipType);
        result &= long2800to3019OrNull.test(prodDate);
        result &= double001to099OrNull.test(speed);
        result &= integer1to9999OrNull.test(crewSize);
        return result;
    }
}
