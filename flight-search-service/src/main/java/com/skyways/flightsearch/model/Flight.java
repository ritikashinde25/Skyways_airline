package com.skyways.flightsearch.model;
 
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
 
@Document(collection = "flights")
public class Flight {
 
    @Id
    private String id;
    private String flightNumber;
    private String origin;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private double price;
    private int availableSeats;
    private List<String> classes;
    private List<String> departureTimes;
 
    public Flight() {}
 
    public Flight(String id, String flightNumber, String origin,
                  String destination, String departureTime,
                  String arrivalTime, double price, int availableSeats,
                  List<String> classes, List<String> departureTimes) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.availableSeats = availableSeats;
        this.classes = classes;
        this.departureTimes = departureTimes;
    }
 
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
 
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
 
    public List<String> getClasses() { return classes; }
    public void setClasses(List<String> classes) { this.classes = classes; }
 
    public List<String> getDepartureTimes() { return departureTimes; }
    public void setDepartureTimes(List<String> departureTimes) { this.departureTimes = departureTimes; }
}
 