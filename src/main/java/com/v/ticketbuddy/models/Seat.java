package com.v.ticketbuddy.models;

import javax.persistence.*;

/**
 * created on: 10/12/20
 * created by: Vishnu
 */

/**
 * A seat in a screen(movie hall).
 */
@Entity
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The seat's number in the screen.
     */
    @Column(nullable = false)
    private String number;

    private SeatType type;

    @ManyToOne
    @JoinColumn(name = "screen_id", referencedColumnName = "id")
    private Screen screen;

    public Seat() {
    }

    public Seat(String number, SeatType type, Screen screen) {
        this.number = number;
        this.type = type;
        this.screen = screen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return number;
    }

    public void setName(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public SeatType getType() {
        return type;
    }

    public void setType(SeatType type) {
        this.type = type;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", type=" + type +
                ", screen=" + screen +
                '}';
    }
}
