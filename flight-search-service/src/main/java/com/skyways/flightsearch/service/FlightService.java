package com.skyways.flightsearch.service;
 
import com.skyways.flightsearch.model.Flight;
import com.skyways.flightsearch.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
 
@Service
public class FlightService {
 
    @Autowired
    private FlightRepository flightRepository;
 
    @PostConstruct
    public void loadSampleData() {
        if (flightRepository.count() == 0) {
            flightRepository.save(new Flight(null, "SW101", "Mumbai",
                "Delhi", "06:00", "08:00", 4500.00, 50,
                Arrays.asList("Economy", "Premium Economy", "Business"),
                Arrays.asList("06:00", "10:00", "14:00", "18:00", "22:00")));
 
            flightRepository.save(new Flight(null, "SW102", "Delhi",
                "Mumbai", "07:00", "09:00", 4200.00, 45,
                Arrays.asList("Economy", "Premium Economy", "Business"),
                Arrays.asList("07:00", "11:00", "15:00", "19:00", "23:00")));
 
            flightRepository.save(new Flight(null, "SW103", "Mumbai",
                "Bangalore", "08:00", "10:00", 3800.00, 60,
                Arrays.asList("Economy", "Premium Economy", "Business"),
                Arrays.asList("08:00", "12:00", "16:00", "20:00")));
 
            flightRepository.save(new Flight(null, "SW104", "Bangalore",
                "Mumbai", "09:00", "11:00", 3900.00, 55,
                Arrays.asList("Economy", "Premium Economy", "Business"),
                Arrays.asList("09:00", "13:00", "17:00", "21:00")));
 
            flightRepository.save(new Flight(null, "SW105", "Delhi",
                "Bangalore", "10:00", "13:00", 5000.00, 40,
                Arrays.asList("Economy", "Premium Economy", "Business"),
                Arrays.asList("10:00", "14:00", "18:00", "22:00")));
 
            flightRepository.save(new Flight(null, "SW106", "Bangalore",
                "Delhi", "11:00", "14:00", 4800.00, 35,
                Arrays.asList("Economy", "Premium Economy", "Business"),
                Arrays.asList("11:00", "15:00", "19:00", "23:00")));
 
            System.out.println("Sample flight data loaded to MongoDB!");
        }
    }
 
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }
 
    public List<Flight> searchFlights(String origin, String destination) {
        return flightRepository
                .findByOriginIgnoreCaseAndDestinationIgnoreCase(
                        origin, destination);
    }
 
    public Flight getFlightById(String id) {
        return flightRepository.findById(id).orElse(null);
    }
}
 