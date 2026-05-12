package com.skyways.booking.model;
 
public class Booking {
 
    private Long id;
    private String username;
    private Long flightId;
    private String flightNumber;
    private String origin;
    private String destination;
    private String bookingDate;
    private String status;
    private double totalPrice;
 
    // Constructors
    public Booking() {}
 
    public Booking(Long id, String username, Long flightId,
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
 
    public Long getFlightId() { return flightId; }
    public void setFlightId(Long flightId) { this.flightId = flightId; }
 
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
 