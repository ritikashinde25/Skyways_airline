package com.skyways.flightsearch.repository;
 
import com.skyways.flightsearch.model.Flight;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
 
@Repository
public interface FlightRepository extends MongoRepository<Flight, String> {
 
    List<Flight> findByOriginIgnoreCaseAndDestinationIgnoreCase(
            String origin, String destination);
    
    List<Flight> findByOriginIgnoreCase(String origin);
    
    List<Flight> findByDestinationIgnoreCase(String destination);
}