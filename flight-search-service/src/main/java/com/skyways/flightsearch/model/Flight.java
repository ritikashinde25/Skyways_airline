package com.skyways.flightsearch.model;
 
public class Flight {
 
    private Long id;
    private String flightNumber;
    private String origin;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private double price;
    private int availableSeats;
 
    // Constructors
    public Flight() {}
 
    public Flight(Long id, String flightNumber, String origin, 
                  String destination, String departureTime, 
                  String arrivalTime, double price, int availableSeats) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.availableSeats = availableSeats;
    }
 
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
 
    public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }
 
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
 
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
 
    public String getDepartureTime() { return departureTime; }
    public void setDepartureTime(String departureTime) { this.departureTime = departureTime; }
 
    public String getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(String arrivalTime) { this.arrivalTime = arrivalTime; }
 
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
 
    public int getAvailableSeats() { return availableSeats; }
    public void setAvailableSeats(int availableSeats) { this.availableSeats = availableSeats; }
}
 