//package com.skyways.payment.kafka;
// 
//import com.skyways.payment.model.Payment;
//import com.skyways.payment.service.PaymentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
// 
//@Component
//public class PaymentEventConsumer {
// 
//    @Autowired
//    private PaymentService paymentService;
// 
//    @KafkaListener(topics = "booking-created", 
//                   groupId = "payment-group")
//    public void consumeBookingEvent(String message) {
//        System.out.println("Payment Service received event: " + message);
// 
//        // Parse event: BOOKING_CREATED|id|username|flightNumber|origin|destination|price
//        String[] parts = message.split("\\|");
//        if (parts[0].equals("BOOKING_CREATED")) {
//            Payment payment = new Payment();
//            payment.setBookingId(Long.parseLong(parts[1]));
//            payment.setUsername(parts[2]);
//            payment.setAmount(Double.parseDouble(parts[6]));
//            payment.setPaymentMethod("AUTO");
//            paymentService.processPayment(payment);
//            System.out.println("Payment processed automatically for booking: " 
//                + parts[1]);
//        }
//    }
//}
// 


package com.skyways.payment.kafka;
 
import com.skyways.payment.model.Payment;
import com.skyways.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
 
@Component
@ConditionalOnProperty(name = "spring.kafka.bootstrap-servers")
public class PaymentEventConsumer {
 
    @Autowired
    private PaymentService paymentService;
 
    @KafkaListener(topics = "booking-created",
                   groupId = "payment-group")
    public void consumeBookingEvent(String message) {
        System.out.println("Payment Service received event: " + message);
        String[] parts = message.split("\\|");
        if (parts[0].equals("BOOKING_CREATED")) {
            Payment payment = new Payment();
            payment.setBookingId(Long.parseLong(parts[1]));
            payment.setUsername(parts[2]);
            payment.setAmount(Double.parseDouble(parts[6]));
            payment.setPaymentMethod("AUTO");
            paymentService.processPayment(payment);
        }
    }
}
 