package com.skyways.payment.repository;
 
import com.skyways.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
 
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
 
    List<Payment> findByUsername(String username);
    
    List<Payment> findByBookingId(Long bookingId);
    
    List<Payment> findByStatus(String status);
}
 