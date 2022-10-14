package com.v.ticketbuddy.models;

import javax.persistence.*;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

/**
 * created on: 05/12/20
 * created by: Vishnu
 */

/**
 * Screening of the movie in a theatre.
 * The scheduling related to movies' screening is not handled. This means overlaps are possible. It's the
 * admin's responsibility to make sure overlaps don't occur.
 */
@Entity
public class Screening {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="movie_id", nullable = false)
    private Movie movie;

    /**
     * The date on which the movie opens
     */
    @Column(nullable = false)
    private Date opening;

    /**
     * The number of days the movie will be screened.
     */
    @Column(nullable = false)
    private Integer periodOfScreening;

    // One show is only mapped to one screen at any given point of time. TODO Need to block this from schema level. Add validation in logic.
    @ManyToOne
    @JoinColumn(name="screen_id", nullable = false)
    private Screen screen;

    /**
     * Auto-populated. For convenience only.
     */
    private Date endOfScreening;

    /**
     * The time at which the movie starts screening daily.
     */
    @Column(nullable = false)
    private Time showTime;

    public Screening() {
    }

    public Screening(Movie movie, Date opening, Integer periodOfScreening, Screen screen, Time showTime) {
        this.movie = movie;
        this.opening = opening;
        this.periodOfScreening = periodOfScreening;
        this.screen = screen;
        Calendar c = Calendar.getInstance();
        c.setTime(opening);
        c.add(Calendar.DAY_OF_MONTH, periodOfScreening);
        this.endOfScreening = c.getTime();
        this.showTime = showTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Date getOpening() {
        return opening;
    }

    public void setOpening(Date opening) {
        this.opening = opening;
    }

    public Integer getPeriodOfScreening() {
        return periodOfScreening;
    }

    public void setPeriodOfScreening(Integer periodOfScreening) {
        this.periodOfScreening = periodOfScreening;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public Date getEndOfScreening() {
        return endOfScreening;
    }

    public void setEndOfScreening(Date endOfScreening) {
        this.endOfScreening = endOfScreening;
    }

    public Time getShowTime() {
        return showTime;
    }

    public void setShowTime(Time showTime) {
        this.showTime = showTime;
    }

    @Override
    public String toString() {
        return "Screening{" +
                "id=" + id +
                ", movie=" + movie +
                ", opening=" + opening +
                ", periodOfScreening=" + periodOfScreening +
                ", screen=" + screen +
                ", endOfScreening=" + endOfScreening +
                ", showTime=" + showTime +
                '}';
    }
}
