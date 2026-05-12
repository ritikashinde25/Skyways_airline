package com.skyways.payment.controller;
 
import com.skyways.payment.model.Payment;
import com.skyways.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
 
@RestController
@RequestMapping("/api/payments")
public class PaymentController {
 
    @Autowired
    private PaymentService paymentService;
 
    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("SkyWays Payment Service is running!");
    }
 
    @PostMapping("/process")
    public ResponseEntity<Payment> processPayment(@RequestBody Payment payment) {
        Payment processed = paymentService.processPayment(payment);
        return ResponseEntity.status(HttpStatus.CREATED).body(processed);
    }
 
    @GetMapping("/all")
    public ResponseEntity<List<Payment>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }
 
    @GetMapping("/user/{username}")
    public ResponseEntity<List<Payment>> getPaymentsByUsername(
            @PathVariable String username) {
        return ResponseEntity.ok(paymentService.getPaymentsByUsername(username));
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<?> getPaymentById(@PathVariable Long id) {
        Payment payment = paymentService.getPaymentById(id);
        if (payment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Payment not found!");
        }
        return ResponseEntity.ok(payment);
    }
 
    @PutMapping("/refund/{id}")
    public ResponseEntity<String> refundPayment(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.refundPayment(id));
    }
}
 