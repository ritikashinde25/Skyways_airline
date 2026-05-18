package com.skyways.booking.repository;
 
import com.skyways.booking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
 
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
 
    List<Booking> findByUsername(String username);
    
    List<Booking> findByStatus(String status);
    
    List<Booking> findByFlightId(String flightId);
}
 