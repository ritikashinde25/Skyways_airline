package com.skyways.notification.service;
 
import com.skyways.notification.model.Notification;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
 
@Service
public class NotificationService {
 
    private List<Notification> notifications = new ArrayList<>();
    private Long idCounter = 1L;
 
    public Notification sendNotification(Notification notification) {
        notification.setId(idCounter++);
        notification.setStatus("SENT");
        notification.setSentAt(java.time.LocalDateTime.now().toString());
        notifications.add(notification);
        return notification;
    }
 
    public Notification sendBookingConfirmation(String username,
                                                String email,
                                                String flightNumber,
                                                String origin,
                                                String destination) {
        Notification notification = new Notification();
        notification.setUsername(username);
        notification.setEmail(email);
        notification.setType("BOOKING_CONFIRMATION");
        notification.setSubject("Booking Confirmed - SkyWays Airlines");
        notification.setMessage("Dear " + username + ", your booking for flight "
                + flightNumber + " from " + origin + " to " + destination
                + " is confirmed. Thank you for choosing SkyWays Airlines!");
        return sendNotification(notification);
    }
 
    public List<Notification> getAllNotifications() {
        return notifications;
    }
 
    public List<Notification> getNotificationsByUsername(String username) {
        return notifications.stream()
                .filter(n -> n.getUsername().equalsIgnoreCase(username))
                .collect(Collectors.toList());
    }
}