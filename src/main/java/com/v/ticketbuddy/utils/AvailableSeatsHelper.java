package com.v.ticketbuddy.utils;

import com.v.ticketbuddy.dto.GetAvailableSeatsDTO;
import com.v.ticketbuddy.models.Screening;
import com.v.ticketbuddy.models.Ticket;
import com.v.ticketbuddy.repositories.ScreeningRepository;
import com.v.ticketbuddy.repositories.SeatRepository;
import com.v.ticketbuddy.repositories.TicketRepository;

import java.util.*;

/**
 * created on: 10/12/20
 * created by: Vishnu
 */

public class AvailableSeatsHelper {

    public static HashSet<String> get(
            GetAvailableSeatsDTO requestBody,
            ScreeningRepository screeningRepository,
            TicketRepository ticketRepository,
            SeatRepository seatRepository) {
        Integer screening_id = requestBody.getScreeningId();
        Optional<Screening> optionalScreening = screeningRepository.findById(screening_id);
        if (!optionalScreening.isPresent()) {
            System.out.println("screening not found");
            return new HashSet<>();
        }

        Screening screening = optionalScreening.get();
        Date date = requestBody.getDate();
        if (!Utils.isMovieScreeningOnDate(screening, date)) {
            System.out.println("Movie not screening on date");
            return new HashSet<>();
        }

        HashSet<String> bookedSeatsSet = new HashSet<>();
        List<Ticket> bookedTickets = ticketRepository.getBookedSeats(date, screening_id);
        for (Ticket t : bookedTickets) {
            String bookedSeats = t.getBookedSeats();
            bookedSeatsSet.addAll(Arrays.asList(bookedSeats.split(",")));
        }

        HashSet<String> seats = new HashSet<>(seatRepository.getSeatNumbers(screening.getScreen().getId()));
        for (String booked : bookedSeatsSet) seats.remove(booked);

        return seats;
    }
}
