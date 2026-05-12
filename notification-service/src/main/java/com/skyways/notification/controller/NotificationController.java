package com.skyways.notification.controller;
 
import com.skyways.notification.model.Notification;
import com.skyways.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
 
@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
 
    @Autowired
    private NotificationService notificationService;
 
    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("SkyWays Notification Service is running!");
    }
 
    @PostMapping("/send")
    public ResponseEntity<Notification> sendNotification(
            @RequestBody Notification notification) {
        Notification sent = notificationService.sendNotification(notification);
        return ResponseEntity.status(HttpStatus.CREATED).body(sent);
    }
 
    @PostMapping("/booking-confirmation")
    public ResponseEntity<Notification> sendBookingConfirmation(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String flightNumber,
            @RequestParam String origin,
            @RequestParam String destination) {
        Notification sent = notificationService.sendBookingConfirmation(
                username, email, flightNumber, origin, destination);
        return ResponseEntity.status(HttpStatus.CREATED).body(sent);
    }
 
    @GetMapping("/all")
    public ResponseEntity<List<Notification>> getAllNotifications() {
        return ResponseEntity.ok(notificationService.getAllNotifications());
    }
 
    @GetMapping("/user/{username}")
    public ResponseEntity<List<Notification>> getNotificationsByUsername(
            @PathVariable String username) {
        return ResponseEntity.ok(
                notificationService.getNotificationsByUsername(username));
    }
}
 