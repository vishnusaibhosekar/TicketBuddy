package com.v.ticketbuddy.models;

import javax.persistence.*;
import java.util.Date;

/**
 * created on: 08/12/20
 * created by: Vishnu
 */

/**
 * The ticket given to the user on booking seats for a movie.
 */
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Stored in DB as comma-separated string.
     */
    @Column(nullable = false)
    private String bookedSeats;

    @ManyToOne
    @JoinColumn(name = "screening_id", referencedColumnName = "id", nullable = false)
    private Screening screening;

    /**
     * The date on which the movie can be viewed or the user is suppose to view.
     */
    @Column(nullable = false)
    private Date screeningDate;

    /**
     * The date on which this ticket was created.
     */
    @Column(nullable = false)
    private Date bookedDate;

    public Ticket() {
    }

    public Ticket(String bookedSeats, Screening screening, Date screeningDate, Date bookedDate) {
        this.bookedSeats = bookedSeats;
        this.screening = screening;
        this.screeningDate = screeningDate;
        this.bookedDate = bookedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(String bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    public Screening getScreening() {
        return screening;
    }

    public void setScreening(Screening screening) {
        this.screening = screening;
    }

    public Date getScreeningDate() {
        return screeningDate;
    }

    public void setScreeningDate(Date screeningDate) {
        this.screeningDate = screeningDate;
    }

    public Date getBookedDate() {
        return bookedDate;
    }

    public void setBookedDate(Date bookedDate) {
        this.bookedDate = bookedDate;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", bookedSeats=" + bookedSeats +
                ", screening=" + screening +
                ", screeningDate=" + screeningDate +
                ", bookedDate=" + bookedDate +
                '}';
    }
}
