package com.skyways.payment.service;
 
import com.skyways.payment.kafka.PaymentEventProducer;
import com.skyways.payment.model.Payment;
import com.skyways.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
 
@Service
public class PaymentService {
 
    @Autowired
    private PaymentRepository paymentRepository;
 
    @Autowired
    private PaymentEventProducer paymentEventProducer;
 
    public Payment processPayment(Payment payment) {
        payment.setStatus("SUCCESS");
        payment.setTransactionId(UUID.randomUUID().toString());
        payment.setPaymentDate(java.time.LocalDate.now().toString());
        Payment saved = paymentRepository.save(payment);
 
        // Publish event to Kafka
        String event = "PAYMENT_PROCESSED|" + saved.getId() + "|"
                + saved.getBookingId() + "|"
                + saved.getUsername() + "|"
                + saved.getAmount() + "|"
                + saved.getTransactionId();
        paymentEventProducer.sendPaymentEvent(event);
 
        return saved;
    }
 
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
 
    public List<Payment> getPaymentsByUsername(String username) {
        return paymentRepository.findByUsername(username);
    }
 
    public Payment getPaymentById(Long id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        return payment.orElse(null);
    }
 
    public String refundPayment(Long id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        if (payment.isEmpty()) {
            return "Payment not found!";
        }
        Payment p = payment.get();
        p.setStatus("REFUNDED");
        paymentRepository.save(p);
        return "Payment refunded successfully!";
    }
}
 