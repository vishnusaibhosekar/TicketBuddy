package com.v.ticketbuddy.repositories;

import com.v.ticketbuddy.models.Seat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * created on: 10/12/20
 * created by: Vishnu
 */
public interface SeatRepository extends CrudRepository<Seat, Long> {

    @Query(
            value = "SELECT s.number FROM seat s WHERE s.screen_id = :screen_id",
            nativeQuery = true
    )
    List<String> getSeatNumbers(@Param("screen_id") Integer screen_id);
}
