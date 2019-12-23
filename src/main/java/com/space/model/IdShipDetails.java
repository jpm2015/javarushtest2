package com.space.model;

public class IdShipDetails extends AbstractShipDetails {
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(String id) {
        this.id = convertId(id);
    }

    @Override
    public boolean validate() {
        return longPositive.test(id);
    }
}
