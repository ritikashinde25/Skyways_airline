package com.skyways.booking.controller;
 
import com.skyways.booking.model.Booking;
import com.skyways.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
 
@RestController
@RequestMapping("/api/bookings")
public class BookingController {
 
    @Autowired
    private BookingService bookingService;
 
    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("SkyWays Booking Service is running!");
    }
 
    @PostMapping("/create")
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        Booking created = bookingService.createBooking(booking);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
 
    @GetMapping("/all")
    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }
 
    @GetMapping("/user/{username}")
    public ResponseEntity<List<Booking>> getBookingsByUsername(
            @PathVariable String username) {
        return ResponseEntity.ok(bookingService.getBookingsByUsername(username));
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<?> getBookingById(@PathVariable Long id) {
        Booking booking = bookingService.getBookingById(id);
        if (booking == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Booking not found!");
        }
        return ResponseEntity.ok(booking);
    }
 
    @PutMapping("/cancel/{id}")
    public ResponseEntity<String> cancelBooking(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.cancelBooking(id));
    }
}
 