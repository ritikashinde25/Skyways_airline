package com.skyways.booking.model;
 
import jakarta.persistence.*;
 
@Entity
@Table(name = "bookings")
public class Booking {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @Column(nullable = false)
    private String username;
 
    @Column(nullable = false)
    private String flightId;
 
    @Column(nullable = false)
    private String flightNumber;
 
    @Column(nullable = false)
    private String origin;
 
    @Column(nullable = false)
    private String destination;
 
    @Column(nullable = false)
    private String bookingDate;
 
    @Column(nullable = false)
    private String status;
 
    @Column(nullable = false)
    private double totalPrice;
 
    // Constructors
    public Booking() {}
 
    public Booking(Long id, String username, String flightId,
                   String flightNumber, String origin, String destination,
                   String bookingDate, String status, double totalPrice) {
        this.id = id;
        this.username = username;
        this.flightId = flightId;
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.bookingDate = bookingDate;
        this.status = status;
        this.totalPrice = totalPrice;
    }
 
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
 
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
 
    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }
 
    public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }
 
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
 
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
 
    public String getBookingDate() { return bookingDate; }
    public void setBookingDate(String bookingDate) { this.bookingDate = bookingDate; }
 
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
 
    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
}
 