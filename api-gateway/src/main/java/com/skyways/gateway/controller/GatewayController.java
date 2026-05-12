package com.skyways.gateway.controller;
 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
@RestController
@RequestMapping("/gateway")
public class GatewayController {
 
    @GetMapping("/status")
    public ResponseEntity<String> status() {
        return ResponseEntity.ok("SkyWays API Gateway is running!");
    }
 
    @GetMapping("/services")
    public ResponseEntity<String> services() {
        String info = "SkyWays Airlines - Microservices Status:\n" +
                "1. User Auth Service     -> http://localhost:8084/api/auth\n" +
                "2. Flight Search Service -> http://localhost:8085/api/flights\n" +
                "3. Booking Service       -> http://localhost:8086/api/bookings\n" +
                "4. Payment Service       -> http://localhost:8087/api/payments\n" +
                "5. Notification Service  -> http://localhost:8088/api/notifications\n" +
                "6. API Gateway           -> http://localhost:8080/gateway";
        return ResponseEntity.ok(info);
    }
}