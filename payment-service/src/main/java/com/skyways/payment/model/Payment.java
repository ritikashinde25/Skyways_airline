package com.skyways.payment.model;
 
public class Payment {
 
    private Long id;
    private Long bookingId;
    private String username;
    private double amount;
    private String paymentMethod;
    private String status;
    private String transactionId;
    private String paymentDate;
 
    public Payment() {}
 
    public Payment(Long id, Long bookingId, String username,
                   double amount, String paymentMethod, String status,
                   String transactionId, String paymentDate) {
        this.id = id;
        this.bookingId = bookingId;
        this.username = username;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.transactionId = transactionId;
        this.paymentDate = paymentDate;
    }
 
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
 
    public Long getBookingId() { return bookingId; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }
 
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
 
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
 
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
 
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
 
    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
 
    public String getPaymentDate() { return paymentDate; }
    public void setPaymentDate(String paymentDate) { this.paymentDate = paymentDate; }
}
 