package com.space.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ship")
public class Ship {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;            // ID корабля

    @Column(name = "name")
    private String name;        // Название корабля (до 50 знаков включительно)
    @Column(name = "planet")
    private String planet;      // Планета пребывания (до 50 знаков включительно)
    @Column(name = "shipType")
    private String shipType;  // Тип корабля
    @Column(name = "prodDate")
    private Date prodDate;      // Дата выпуска. Диапазон значений года 2800..3019 включительно
    @Column(name = "isUsed")
    private Boolean isUsed;     // isUsed Использованный / новый
    @Column(name = "speed")
    private Double speed;       // Максимальная скорость. 0,01..0,99 включительно. Математическое округление до сотых.
    @Column(name = "crewSize")
    private Integer crewSize;   // Количество членов экипажа. 1..9999 включительно.
    @Column(name = "rating")
    private Double rating;      // Рейтинг корабля. Используй математическое округление до сотых.

    public Ship() {}
    public Ship(Long id, String name, String planet, String shipType, Date prodDate, Boolean isUsed, Double speed, Integer crewSize) {
        this.id = id;
        this.name = name;
        this.planet = planet;
        this.shipType = shipType;
        this.prodDate = prodDate;
        this.isUsed = isUsed;
        this.speed = ((double) (Math.round(100 * speed))) / 100;
        this.crewSize = crewSize;
        this.rating = calcRating();
    }
    public Ship(AddShipDetails shipDetails) {
        this(null, shipDetails.getName(), shipDetails.getPlanet(), shipDetails.getShipType(),
                shipDetails.getProdDate(), shipDetails.getIsUsed(), shipDetails.getSpeed(),
                shipDetails.getCrewSize());
    }

    public void syncWith(EditShipDetails shipDetails) {
        String name = shipDetails.getName();
        if (name != null ) this.name = name;

        String planet = shipDetails.getPlanet();
        if (planet != null ) this.planet = planet;

        String shipType = shipDetails.getShipType();
        if (shipType != null) this.shipType = shipType;

        Date prodDate = shipDetails.getProdDate();
        if (prodDate != null) this.prodDate = prodDate;

        Boolean isUsed = shipDetails.getIsUsed();
        if (isUsed != null) this.isUsed = isUsed;

        Double speed = shipDetails.getSpeed();
        if (speed != null) this.speed = speed;

        Integer crewSize = shipDetails.getCrewSize();
        if (crewSize != null) this.crewSize = crewSize;

        this.rating = calcRating();
    }

    public static Double calcRating(boolean isUsed, double speed, Date prodDate) {
        double k = isUsed ? 0.5 : 1;
        double v = speed;
        int y0 = 3019;
        int y1 = prodDate.getYear() + 1900;
        double result100 = 100 * (80 * v * k) / (y0 - y1 +1);
        return ((double) Math.round(result100)) / 100;
    }

    private Double calcRating() {
        return Ship.calcRating(isUsed, speed, prodDate);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPlanet() { return planet; }
    public void setPlanet(String planet) { this.planet = planet; }

    public String getShipType() { return shipType; }
    public void setShipType(String shipType) { this.shipType = shipType; }

    public Date getProdDate() { return prodDate; }
    public void setProdDate(Date prodDate) { this.prodDate = prodDate; }

    public Boolean getIsUsed() { return isUsed; }
    public void setIsUsed(Boolean used) { isUsed = used; }

    public Double getSpeed() { return speed; }
    public void setSpeed(Double speed) { this.speed = speed; }

    public Integer getCrewSize() { return crewSize; }
    public void setCrewSize(Integer crewSize) { this.crewSize = crewSize; }

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"name\":\"" + name + '\"' +
                ", \"planet\":\"" + planet + '\"' +
                ", \"shipType\":\"" + shipType + '\"' +
                ", \"prodDate\":" + prodDate.getTime() +
                ", \"isUsed\":" + isUsed +
                ", \"speed\":" + speed +
                ", \"crewSize\":" + crewSize +
                ", \"rating\":" + rating +
                '}';
    }
}
