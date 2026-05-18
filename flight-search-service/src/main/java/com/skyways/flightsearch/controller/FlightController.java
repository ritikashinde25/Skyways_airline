package com.skyways.flightsearch.controller;
 
import com.skyways.flightsearch.model.Flight;
import com.skyways.flightsearch.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
 
@RestController
@RequestMapping("/api/flights")
public class FlightController {
 
    @Autowired
    private FlightService flightService;
 
    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("SkyWays Flight Search Service is running!");
    }
 
    @GetMapping("/all")
    public ResponseEntity<List<Flight>> getAllFlights() {
        return ResponseEntity.ok(flightService.getAllFlights());
    }
 
    @GetMapping("/search")
    public ResponseEntity<?> searchFlights(
            @RequestParam String origin,
            @RequestParam String destination) {
        List<Flight> results = flightService.searchFlights(origin, destination);
        if (results.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No flights found from " + origin + " to " + destination);
        }
        return ResponseEntity.ok(results);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<?> getFlightById(@PathVariable String id) {
        Flight flight = flightService.getFlightById(id);
        if (flight == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Flight not found with id: " + id);
        }
        return ResponseEntity.ok(flight);
    }
}
 