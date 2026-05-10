package com.skyways.flightsearch.service;
 
import com.skyways.flightsearch.model.Flight;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
 
@Service
public class FlightService {
 
    // Sample flights data
    private List<Flight> flights = new ArrayList<>();
 
    public FlightService() {
        flights.add(new Flight(1L, "SW101", "Mumbai", "Delhi",
                "08:00", "10:00", 4500.00, 50));
        flights.add(new Flight(2L, "SW102", "Delhi", "Mumbai",
                "12:00", "14:00", 4200.00, 45));
        flights.add(new Flight(3L, "SW103", "Mumbai", "Bangalore",
                "09:00", "11:00", 3800.00, 60));
        flights.add(new Flight(4L, "SW104", "Bangalore", "Mumbai",
                "15:00", "17:00", 3900.00, 55));
        flights.add(new Flight(5L, "SW105", "Delhi", "Bangalore",
                "10:00", "13:00", 5000.00, 40));
        flights.add(new Flight(6L, "SW106", "Bangalore", "Delhi",
                "16:00", "19:00", 4800.00, 35));
    }
 
    public List<Flight> getAllFlights() {
        return flights;
    }
 
    public List<Flight> searchFlights(String origin, String destination) {
        return flights.stream()
                .filter(f -> f.getOrigin().equalsIgnoreCase(origin)
                        && f.getDestination().equalsIgnoreCase(destination))
                .collect(Collectors.toList());
    }
 
    public Flight getFlightById(Long id) {
        return flights.stream()
                .filter(f -> f.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}