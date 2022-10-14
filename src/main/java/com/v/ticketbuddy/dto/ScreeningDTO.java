package com.v.ticketbuddy.dto;

import java.sql.Time;
import java.util.Date;

/**
 * created on: 10/12/20
 * created by: Vishnu
 */
public class ScreeningDTO {

    private Integer movie_id;
    private Integer period_of_screening;
    private Date opening;
    private Integer screen_id;
    private Time showTime;

    public ScreeningDTO() {
    }

    public ScreeningDTO(Integer movie_id, Integer period_of_screening, Date opening, Integer screen_id, Time showTime) {
        this.movie_id = movie_id;
        this.period_of_screening = period_of_screening;
        this.opening = opening;
        this.screen_id = screen_id;
        this.showTime = showTime;
    }

    public Integer getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(Integer movie_id) {
        this.movie_id = movie_id;
    }

    public Integer getPeriod_of_screening() {
        return period_of_screening;
    }

    public void setPeriod_of_screening(Integer period_of_screening) {
        this.period_of_screening = period_of_screening;
    }

    public Date getOpening() {
        return opening;
    }

    public void setOpening(Date opening) {
        this.opening = opening;
    }

    public Integer getScreen_id() {
        return screen_id;
    }

    public void setScreen_id(Integer screen_id) {
        this.screen_id = screen_id;
    }

    public Time getShowTime() {
        return showTime;
    }

    public void setShowTime(Time showTime) {
        this.showTime = showTime;
    }

    @Override
    public String toString() {
        return "ScreeningDTO{" +
                "movie_id=" + movie_id +
                ", period_of_screening=" + period_of_screening +
                ", opening=" + opening +
                ", screen_id=" + screen_id +
                ", showTime=" + showTime +
                '}';
    }
}
