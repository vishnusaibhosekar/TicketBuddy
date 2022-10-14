package com.v.ticketbuddy.dto;

import java.sql.Date;
import java.util.List;

/**
 * created on: 07/12/20
 * created by: Vishnu
 */
public class SeatBookingDTO {

    private Integer screeningId;

    private List<String> seats;

    private Date dateOfScreening;

    public SeatBookingDTO() {
    }

    public Date getDateOfScreening() {
        return dateOfScreening;
    }

    public void setDateOfScreening(Date dateOfScreening) {
        this.dateOfScreening = dateOfScreening;
    }

    public SeatBookingDTO(Integer screeningId, List<String> seats) {
        this.screeningId = screeningId;
        this.seats = seats;
    }

    public Integer getScreeningId() {
        return screeningId;
    }

    public void setScreeningId(Integer screeningId) {
        this.screeningId = screeningId;
    }

    public List<String> getSeats() {
        return seats;
    }

    public void setSeats(List<String> seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "BookSeats{" +
                "screeningId=" + screeningId +
                ", seats=" + seats +
                ", dateOfScreening=" + dateOfScreening +
                '}';
    }
}
