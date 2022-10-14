package com.v.ticketbuddy.controllers;

import com.v.ticketbuddy.models.City;
import com.v.ticketbuddy.repositories.CityRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * created on: 10/12/20
 * created by: Vishnu
 */

@Api(value = "cityAPI", description = "City related APIs")
@RestController
@RequestMapping("/api/city")
public class CityController {

    @Autowired
    CityRepository cityRepository;

    @ApiOperation(value = "Add a city")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return the added city")
    })
    @PostMapping()
    public ResponseEntity<String> addCity(@RequestParam String name) {
        City c = cityRepository.save(new City(name));
        return ResponseEntity.ok(c.toString());
    }

    @ApiOperation(value = "Get all cities")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of cities")
    })
    @GetMapping("/all")
    public ResponseEntity<Iterable<City>> getAllCities() {
        return ResponseEntity.ok(cityRepository.findAll());
    }

    @ApiOperation(value = "Delete a city with id")
    @ApiResponse(code = 204, message = "City deleted")
    @DeleteMapping()
    public ResponseEntity<String> deleteCity(@RequestParam Integer city_id) {
        cityRepository.deleteById(city_id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}