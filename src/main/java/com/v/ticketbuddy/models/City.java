package com.v.ticketbuddy.models;

import javax.persistence.*;

/**
 * created on: 05/12/20
 * created by: Vishnu
 */

/**
 * A city somewhere.
 */
@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    public City(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public City() {
    }

    public City(String city) {
        this.name = city;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
