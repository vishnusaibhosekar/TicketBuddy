package com.v.ticketbuddy.controllers;

import com.v.ticketbuddy.dto.ScreeningDTO;
import com.v.ticketbuddy.models.City;
import com.v.ticketbuddy.models.Movie;
import com.v.ticketbuddy.models.Screen;
import com.v.ticketbuddy.models.Screening;
import com.v.ticketbuddy.repositories.CityRepository;
import com.v.ticketbuddy.repositories.MovieRepository;
import com.v.ticketbuddy.repositories.ScreenRepository;
import com.v.ticketbuddy.repositories.ScreeningRepository;
import com.v.ticketbuddy.utils.Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

/**
 * created on: 10/12/20
 * created by: Vishnu
 */

@Api(value = "ScreeningAPI", description = "Screening related APIs")
@RestController
@RequestMapping("/api/screening")
public class ScreeningController {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ScreenRepository screenRepository;

    @Autowired
    ScreeningRepository screeningRepository;

    @Autowired
    CityRepository cityRepository;

    @ApiOperation(value = "Add new movie screenings")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Return the added screening")
            }
    )
    @PostMapping("")
    public ResponseEntity<String> addScreening(@RequestBody ScreeningDTO screeningDTO) {
        Optional<Movie> optionalMovie = movieRepository.findById(screeningDTO.getMovie_id());
        Optional<Screen> optionalScreen = screenRepository.findById(screeningDTO.getScreen_id());
        Date opening = screeningDTO.getOpening();
        if (!Utils.isDateInFuture(opening)) {
            return ResponseEntity.badRequest().body("Date of opening is not in future");
        }

        if (!optionalMovie.isPresent() || !optionalScreen.isPresent()) {
            return ResponseEntity.badRequest().body("Movie or Screen or both are not present");
        }

        Screening s = screeningRepository.save(new Screening(optionalMovie.get(), opening, screeningDTO.getPeriod_of_screening(), optionalScreen.get(), screeningDTO.getShowTime()));
        return ResponseEntity.ok(s.toString());
    }

    @ApiOperation(value = "Get cities which are showing movies")
    @GetMapping("/find_cities")
    public ResponseEntity<Iterable<City>> findCitiesWithScreening() {
        return ResponseEntity.ok(cityRepository.findAllById(screeningRepository.findCitiesWithScreening()));
    }

    @ApiOperation(value = "Get movie screenings in a city")
    @GetMapping("/find_screenings")
    public ResponseEntity<Iterable<Screening>> findScreeningsInCity(@RequestParam Integer city_id) {
        return ResponseEntity.ok(screeningRepository.findAllById(screeningRepository.findMoviesWithCity(city_id)));
    }

    @ApiOperation(value = "Get screening from id")
    @GetMapping()
    public ResponseEntity<Screening> getScreeningFromId(@RequestParam Integer screening_id) {
        Optional<Screening> optionalScreening = screeningRepository.findById(screening_id);
        return optionalScreening.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Get all screenings")
    @GetMapping("/all")
    public ResponseEntity<Iterable<Screening>> getAllScreenings() {
        return ResponseEntity.ok(screeningRepository.findAll());
    }

    @ApiOperation(value = "Delete screening")
    @DeleteMapping()
    public ResponseEntity<String> deleteScreening(@RequestParam Integer screening_id) {
        screeningRepository.deleteById(screening_id);
        return ResponseEntity.noContent().build();
    }

}
