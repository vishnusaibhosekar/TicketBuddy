package com.v.ticketbuddy.repositories;

import com.v.ticketbuddy.models.Ticket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * created on: 08/12/20
 * created by: Vishnu
 */

public interface TicketRepository extends CrudRepository<Ticket, Long> {

    @Query(value = "SELECT * FROM ticket t WHERE t.screening_id = :screening_id AND " +
            "t.screening_date = :date", nativeQuery = true)
    List<Ticket> getBookedSeats(@Param("date") Date date, @Param("screening_id") Integer screening_id);
}
