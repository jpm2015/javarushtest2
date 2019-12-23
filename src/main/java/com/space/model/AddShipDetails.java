package com.space.model;

import java.util.Date;

public class AddShipDetails extends AbstractShipDetails{
    protected String name;
    protected String planet;
    protected String shipType;
    protected Boolean isUsed;
    protected Long prodDate;
    protected Double speed;
    protected Integer crewSize;
    protected Double rating;

    public String getName() {
        return name;
    }

    public String getPlanet() {
        return planet;
    }

    public String getShipType() {
        return shipType;
    }

    public Boolean getIsUsed() {
        return isUsed;
    }

    public Date getProdDate() {
        return long2800to3019.test(prodDate) ? new Date(prodDate) : null;
    }

    public Double getSpeed() {
        return speed;
    }

    public Integer getCrewSize() {
        return crewSize;
    }

    @Override
    public boolean validate() {
        boolean result = true;
        result &= stringLength1to50.test(name);
        result &= stringLength1to50.test(planet);
        result &= stringIsShipType.test(shipType);
        result &= long2800to3019.test(prodDate);
        if (!objectNotNull.test(isUsed)) {
            isUsed = false;
        }
        result &= double001to099.test(speed);
        result &= integer1to9999.test(crewSize);
        if (result) rating = Ship.calcRating(this.getIsUsed(), this.getSpeed(), this.getProdDate());
        return result;
    }
}
