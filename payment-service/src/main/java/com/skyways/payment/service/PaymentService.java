package com.skyways.payment.service;
 
import com.skyways.payment.kafka.PaymentEventProducer;
import com.skyways.payment.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
 
@Service
public class PaymentService {
 
    private List<Payment> payments = new ArrayList<>();
    private Long idCounter = 1L;
 
    @Autowired
    private PaymentEventProducer paymentEventProducer;
 
    public Payment processPayment(Payment payment) {
        payment.setId(idCounter++);
        payment.setStatus("SUCCESS");
        payment.setTransactionId(UUID.randomUUID().toString());
        payment.setPaymentDate(java.time.LocalDate.now().toString());
        payments.add(payment);
 
        // Publish event to Kafka
        String event = "PAYMENT_PROCESSED|" + payment.getId() + "|"
                + payment.getBookingId() + "|"
                + payment.getUsername() + "|"
                + payment.getAmount() + "|"
                + payment.getTransactionId();
        paymentEventProducer.sendPaymentEvent(event);
 
        return payment;
    }
 
    public List<Payment> getAllPayments() {
        return payments;
    }
 
    public List<Payment> getPaymentsByUsername(String username) {
        return payments.stream()
                .filter(p -> p.getUsername().equalsIgnoreCase(username))
                .collect(Collectors.toList());
    }
 
    public Payment getPaymentById(Long id) {
        return payments.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
 
    public String refundPayment(Long id) {
        Payment payment = getPaymentById(id);
        if (payment == null) {
            return "Payment not found!";
        }
        payment.setStatus("REFUNDED");
        return "Payment refunded successfully!";
    }
}
 