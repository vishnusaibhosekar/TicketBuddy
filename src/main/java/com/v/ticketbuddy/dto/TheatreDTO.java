package com.v.ticketbuddy.dto;

/**
 * created on: 10/12/20
 * created by: Vishnu
 */

public class TheatreDTO {

    private String name;
    private Integer city_id;
    private Integer owner_id;

    public TheatreDTO(String name, Integer city_id, Integer owner_id) {
        this.name = name;
        this.city_id = city_id;
        this.owner_id = owner_id;
    }

    public TheatreDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCity_id() {
        return city_id;
    }

    public void setCity_id(Integer city_id) {
        this.city_id = city_id;
    }

    public Integer getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Integer owner_id) {
        this.owner_id = owner_id;
    }

    @Override
    public String toString() {
        return "TheatreDTO{" +
                "name=" + name +
                ", city_id=" + city_id +
                ", owner_id=" + owner_id +
                '}';
    }
}
