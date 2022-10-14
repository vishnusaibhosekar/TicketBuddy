package com.v.ticketbuddy.repositories;

import com.v.ticketbuddy.models.Screening;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * created on: 06/12/20
 * created by: Vishnu
 */

public interface ScreeningRepository extends CrudRepository<Screening, Integer> {

    @Query(value = "SELECT DISTINCT t.city_id FROM " +
            "((screening sc INNER JOIN screen s ON sc.screen_id = s.id) INNER JOIN theatre t ON s.theatre_id = t.id)",
            nativeQuery = true
    )
    List<Integer> findCitiesWithScreening();

    @Query(value = "SELECT sc.id FROM " +
            "((screening sc INNER JOIN screen s ON sc.screen_id = s.id) " +
            "INNER JOIN theatre t ON s.theatre_id = t.id) WHERE t.city_id = :city_id", nativeQuery = true)
    List<Integer> findMoviesWithCity(@Param("city_id") Integer city_id);

    @Query(value = "SELECT COUNT(s.id) FROM screening s " +
            "WHERE s.end_of_screening >= :start AND s.screen_id = :screen_id AND s.opening <= :end", nativeQuery = true)
    Integer getCountOfRunningScreeningsBetweenDates(@Param("start") Date start, @Param("end") Date end, @Param("screen_id") Integer screen_id);
}
