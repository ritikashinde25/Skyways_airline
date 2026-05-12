package com.skyways.notification.kafka;
 
import com.skyways.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
 
@Component
public class NotificationEventConsumer {
 
    @Autowired
    private NotificationService notificationService;
 
    @KafkaListener(topics = "payment-processed",
                   groupId = "notification-group")
    public void consumePaymentEvent(String message) {
        System.out.println("Notification Service received event: " + message);
 
        // Parse event: PAYMENT_PROCESSED|id|bookingId|username|amount|transactionId
        String[] parts = message.split("\\|");
        if (parts[0].equals("PAYMENT_PROCESSED")) {
            String username = parts[3];
            String bookingId = parts[2];
            String amount = parts[4];
            String transactionId = parts[5];
 
            notificationService.sendBookingConfirmation(
                username,
                username + "@skyways.com",
                "SW-FLIGHT",
                "Origin",
                "Destination"
            );
 
            System.out.println("Confirmation notification sent to: " + username);
        }
    }
}
 