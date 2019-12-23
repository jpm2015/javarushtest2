package com.space.model;

public class SelectListShipDetails extends SelectCountShipDetails {
    protected String order;
    protected Integer pageNumber;
    protected Integer pageSize;

    public String getOrder() {
        return order;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setOrder(String order) {
        this.order = convertShipOrder(order).getFieldName();
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = convertPageNumberSize(pageNumber);
    }

    public void setPageSize(String pageSize) {
        this.pageSize = convertPageNumberSize(pageSize);
    }

    @Override
    public boolean validate() {
        boolean result = super.validate();
        result &= stringIsShipOrderOrNull.test(order);
        if (pageNumber != null) {
            result &= integerNotNegative.test(pageNumber);
        } else pageNumber = 0;
        if (pageSize != null) {
            result &= integerPositive.test(pageSize);
        } else pageSize = 3;
        return result;
    }
}
