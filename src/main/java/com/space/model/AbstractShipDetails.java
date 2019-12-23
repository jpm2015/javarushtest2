package com.space.model;

import com.space.controller.ShipOrder;

import java.util.*;
import java.util.function.Predicate;

public abstract class AbstractShipDetails {
    protected Predicate<Object> objectNotNull = o -> (o != null);
    protected Predicate<Long> longPositive = l -> (l != null) && (l > 0);
    protected Predicate<String> stringLength1to50 = s -> (s != null) && (s.length() > 0) && (s.length() <= 50);
    protected Predicate<String> stringLength1to50OrNull = s -> (s == null) || (stringLength1to50.test(s));
    protected Predicate<String> stringIsShipType = s -> {
        if (s == null) return false;
        try {
            ShipType.valueOf(s);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    };
    protected Predicate<String> stringIsShipTypeOrNull = s -> (s == null) || stringIsShipType.test(s);
    protected Predicate<Long> long2800to3019 = l -> (l != null) && (l > 0) && (new Date(l).getYear() >= (2800 - 1900)) && (new Date(l).getYear() <= (3019 - 1900));
    protected Predicate<Long> long2800to3019OrNull = l -> (l == null) || (long2800to3019.test(l));
       protected Predicate<Double> double001to099 = d -> (d != null) && (d >= 0.01) && (d <= 0.99);
    protected Predicate<Double> double001to099OrNull = d -> (d == null) || (double001to099.test(d));
    protected Predicate<Integer> integer1to9999 = i -> (i != null) && (i >= 1) && (i <= 9999);
    protected Predicate<Integer> integer1to9999OrNull = i -> (i == null) || (integer1to9999.test(i));
    protected Predicate<Integer> integerPositive = i -> (i != null) && (i > 0);
    protected Predicate<Integer> integerNotNegative = i -> (i != null) && (i >= 0);
    protected Predicate<String> stringIsShipOrder = s -> {
        if (s == null) return false;
        boolean found = false;
        ShipOrder[] orders = ShipOrder.values();
        for (ShipOrder shipOrder : orders) {
            if (shipOrder.getFieldName().equals(s)) found = true;
        }
        return found;
    };
    protected Predicate<String> stringIsShipOrderOrNull = s -> (s == null) || (stringIsShipOrder.test(s));

    public abstract boolean validate();

    protected Long convertId(String param) {
        try {
            return Long.valueOf(param);
        } catch (NumberFormatException e) {
            return null;
        }
    }

     protected ShipType convertShipType(String param) {
        try {
            return ShipType.valueOf(param);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    protected Date convertProdDate(String param) {
        try {
            long paramLong = Long.parseLong(param);
            if (paramLong < 0) return null;
            return new Date(paramLong);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    protected Boolean convertUsed(String param) {
        String lowerParam = param.toLowerCase();
        if (("true".equals(lowerParam)) || ("false".equals(lowerParam))) {
            return Boolean.valueOf(param);
        } else {
            return null;
        }
    }

    protected Double convertSpeed(String param) {
        try {
            return ((double) (Math.round(100 * Double.parseDouble(param)))) / 100;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    protected Integer convertCrewSize(String param) {
        try {
            return Integer.valueOf(param);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    protected Double convertRating(String param) {
        try {
            return ((double) (Math.round(100 * Double.parseDouble(param)))) / 100;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    protected Integer convertPageNumberSize(String param) {
        try {
            return Integer.valueOf(param);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    protected ShipOrder convertShipOrder(String param) {
        try {
            return ShipOrder.valueOf(param);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
