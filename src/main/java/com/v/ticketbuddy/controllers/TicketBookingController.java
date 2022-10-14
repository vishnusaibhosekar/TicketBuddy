package com.v.ticketbuddy.controllers;

import com.v.ticketbuddy.dto.GetAvailableSeatsDTO;
import com.v.ticketbuddy.dto.SeatBookingDTO;
import com.v.ticketbuddy.models.*;
import com.v.ticketbuddy.repositories.ScreeningRepository;
import com.v.ticketbuddy.repositories.SeatRepository;
import com.v.ticketbuddy.repositories.TicketRepository;
import com.v.ticketbuddy.utils.AvailableSeatsHelper;
import com.v.ticketbuddy.utils.Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * created on: 08/12/20
 * created by: Vishnu
 */

@Api(value = "ticketbuddy", description = "Book tickets and check seat availability")
@RestController
@RequestMapping(path = "/api/bms")
public class TicketBookingController {

    @Autowired
    private ScreeningRepository screeningRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private SeatRepository seatRepository;

    @ApiOperation(value = "Get the seats which are available for booking")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Returns list of available seats")
            }
    )
    @Transactional(isolation = Isolation.SERIALIZABLE)
    @PostMapping(path = "/get_available_seats")
    public ResponseEntity<List<String>> getAvailableSeats(@RequestBody GetAvailableSeatsDTO requestBody) {
        List<String> seats = new ArrayList<>();
        AvailableSeatsHelper.get(requestBody, screeningRepository, ticketRepository, seatRepository)
                .stream().sorted().forEach(seats::add);
        return ResponseEntity.ok(seats);
    }


    @ApiOperation(value = "Book tickets for movies")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Returns the ticket details")
            }
    )
    @Transactional(isolation = Isolation.SERIALIZABLE)
    @PostMapping(path = "/book_seats")
    public ResponseEntity<String> bookSeats(@RequestBody SeatBookingDTO bookSeats) {
        Integer screening_id = bookSeats.getScreeningId();
        Date date = bookSeats.getDateOfScreening();
        List<String> seatsToBook = bookSeats.getSeats();

        if (!Utils.isDateInFuture(date)) {
            return ResponseEntity.badRequest().body("screening for the given date is over.");
        }

        HashSet<String> availableSeats = AvailableSeatsHelper.get(new GetAvailableSeatsDTO(screening_id, date),
                screeningRepository, ticketRepository, seatRepository);

        // check if seats are not booked
        for (String seat : seatsToBook) {
            if (!availableSeats.contains(seat)) {
                return ResponseEntity.badRequest().body("Some error occurred while booking");
            }
        }

        String seats = String.join(",", seatsToBook);
        Ticket t = new Ticket(seats, screeningRepository.findById(screening_id).get(), date, new Date());
        System.out.println(t);
        ticketRepository.save(t);

        return ResponseEntity.ok(t.toString());
    }
}
