package com.v.ticketbuddy.controllers;

import com.v.ticketbuddy.models.Movie;
import com.v.ticketbuddy.repositories.MovieRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * created on: 10/12/20
 * created by: Vishnu
 */

@Api(value = "movieAPI", description = "Movie related APIs")
@RestController
@RequestMapping("/api/movie")
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    @ApiOperation(value = "Add a movie")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Added movie is returned"),
            @ApiResponse(code = 400, message = "Movie already exists")
    })
    @PostMapping()
    public ResponseEntity<String> addMovie(@RequestParam String name) {
        if (movieRepository.findByName(name).size() > 1) {
            return ResponseEntity.badRequest().body("Movie already exists");
        }
        return ResponseEntity.ok(movieRepository.save(new Movie(name)).toString());
    }

    @ApiOperation(value = "Get movie from id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return the movie found"),
            @ApiResponse(code = 404, message = "No movie exists with the id")
    })
    @GetMapping()
    public ResponseEntity<Movie> getMovieById(@RequestParam Integer movie_id) {
        Optional<Movie> optionalMovie = movieRepository.findById(movie_id);
        return optionalMovie.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Get all movies")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All movies are returned")
    })
    @GetMapping("/all")
    public ResponseEntity<Iterable<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieRepository.findAll());
    }

    @ApiOperation(value = "Delete a movie")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Deleted movie")
    })
    @DeleteMapping()
    public ResponseEntity<String> deleteMovie(@RequestParam Integer movie_id) {
        movieRepository.deleteById(movie_id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
