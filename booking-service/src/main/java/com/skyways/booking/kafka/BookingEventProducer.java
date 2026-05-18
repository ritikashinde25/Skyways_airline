//package com.skyways.booking.kafka;
// 
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Component;
// 
//@Component
//public class BookingEventProducer {
// 
//    private static final String TOPIC = "booking-created";
// 
//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;
// 
//    public void sendBookingEvent(String message) {
//        System.out.println("Publishing booking event to Kafka: " + message);
//        kafkaTemplate.send(TOPIC, message);
//    }
//}


package com.skyways.booking.kafka;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
 
@Component
public class BookingEventProducer {
 
    private static final String TOPIC = "booking-created";
 
    @Autowired(required = false)
    private KafkaTemplate<String, String> kafkaTemplate;
 
    public void sendBookingEvent(String message) {
        if (kafkaTemplate != null) {
            System.out.println("Publishing booking event to Kafka: " + message);
            kafkaTemplate.send(TOPIC, message);
        } else {
            System.out.println("Kafka not available. Event skipped: " + message);
        }
    }
}
 