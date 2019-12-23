package com.space.model;

import java.util.Date;

public class SelectCountShipDetails extends AbstractShipDetails {
    protected String name;
    protected String planet;
    protected String shipType;
    protected Boolean isUsed;
    protected Date after;
    protected Date before;
    protected Double minSpeed;
    protected Double maxSpeed;
    protected Integer minCrewSize;
    protected Integer maxCrewSize;
    protected Double minRating;
    protected Double maxRating;

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

    public Date getAfter() {
        return after;
    }

    public Date getBefore() {
        return before;
    }

    public Double getMinSpeed() {
        return minSpeed;
    }

    public Double getMaxSpeed() {
        return maxSpeed;
    }

    public Integer getMinCrewSize() {
        return minCrewSize;
    }

    public Integer getMaxCrewSize() {
        return maxCrewSize;
    }

    public Double getMinRating() {
        return minRating;
    }

    public Double getMaxRating() {
        return maxRating;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    public void setShipType(String shipType) {
        this.shipType = convertShipType(shipType).name();
    }

    public void setIsUsed(String used) {
        isUsed = convertUsed(used);
    }

    public void setAfter(String after) {
        this.after = convertProdDate(after);
    }

    public void setBefore(String before) {
        this.before = convertProdDate(before);
        this.before.setHours(0);
    }

    public void setMinSpeed(String minSpeed) {
        this.minSpeed = convertSpeed(minSpeed);
    }

    public void setMaxSpeed(String maxSpeed) {
        this.maxSpeed = convertSpeed(maxSpeed);
    }

    public void setMinCrewSize(String minCrewSize) {
        this.minCrewSize = convertCrewSize(minCrewSize);
    }

    public void setMaxCrewSize(String maxCrewSize) {
        this.maxCrewSize = convertCrewSize(maxCrewSize);
    }

    public void setMinRating(String minRating) {
        this.minRating = convertRating(minRating);
    }

    public void setMaxRating(String maxRating) {
        this.maxRating = convertRating(maxRating);
    }

    @Override
    public boolean validate() {
        boolean result = true;
        if (shipType != null) result = stringIsShipTypeOrNull.test(shipType);
        return result;
    }
}
