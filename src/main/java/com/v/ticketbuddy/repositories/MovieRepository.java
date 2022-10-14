package com.v.ticketbuddy.repositories;

import com.v.ticketbuddy.models.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * created on: 06/12/20
 * created by: Vishnu
 */

public interface MovieRepository extends CrudRepository<Movie, Integer> {
    List<Movie> findByName(String name);
}
