package com.skyways.booking.service;
 
import com.skyways.booking.kafka.BookingEventProducer;
import com.skyways.booking.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
 
@Service
public class BookingService {
 
    private List<Booking> bookings = new ArrayList<>();
    private Long idCounter = 1L;
 
    @Autowired
    private BookingEventProducer bookingEventProducer;
 
    public Booking createBooking(Booking booking) {
        booking.setId(idCounter++);
        booking.setStatus("CONFIRMED");
        bookings.add(booking);
 
        // Publish event to Kafka
        String event = "BOOKING_CREATED|" + booking.getId() + "|"
                + booking.getUsername() + "|"
                + booking.getFlightNumber() + "|"
                + booking.getOrigin() + "|"
                + booking.getDestination() + "|"
                + booking.getTotalPrice();
        bookingEventProducer.sendBookingEvent(event);
 
        return booking;
    }
 
    public List<Booking> getAllBookings() {
        return bookings;
    }
 
    public List<Booking> getBookingsByUsername(String username) {
        return bookings.stream()
                .filter(b -> b.getUsername().equalsIgnoreCase(username))
                .collect(Collectors.toList());
    }
 
    public Booking getBookingById(Long id) {
        return bookings.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
 
    public String cancelBooking(Long id) {
        Booking booking = getBookingById(id);
        if (booking == null) {
            return "Booking not found!";
        }
        booking.setStatus("CANCELLED");
        return "Booking cancelled successfully!";
    }
}
 