package com.skyways.booking.service;
 
import com.skyways.booking.kafka.BookingEventProducer;
import com.skyways.booking.model.Booking;
import com.skyways.booking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
 
@Service
public class BookingService {
 
    @Autowired
    private BookingRepository bookingRepository;
 
    @Autowired
    private BookingEventProducer bookingEventProducer;
 
    public Booking createBooking(Booking booking) {
        booking.setStatus("CONFIRMED");
        Booking saved = bookingRepository.save(booking);
 
        // Publish event to Kafka
        String event = "BOOKING_CREATED|" + saved.getId() + "|"
                + saved.getUsername() + "|"
                + saved.getFlightNumber() + "|"
                + saved.getOrigin() + "|"
                + saved.getDestination() + "|"
                + saved.getTotalPrice();
        bookingEventProducer.sendBookingEvent(event);
 
        return saved;
    }
 
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
 
    public List<Booking> getBookingsByUsername(String username) {
        return bookingRepository.findByUsername(username);
    }
 
    public Booking getBookingById(Long id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        return booking.orElse(null);
    }
 
    public String cancelBooking(Long id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        if (booking.isEmpty()) {
            return "Booking not found!";
        }
        Booking b = booking.get();
        b.setStatus("CANCELLED");
        bookingRepository.save(b);
        return "Booking cancelled successfully!";
    }
}
 