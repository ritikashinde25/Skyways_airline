package com.skyways.payment.kafka;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
 
@Component
public class PaymentEventProducer {
 
    private static final String TOPIC = "payment-processed";
 
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
 
    public void sendPaymentEvent(String message) {
        System.out.println("Publishing payment event to Kafka: " + message);
        kafkaTemplate.send(TOPIC, message);
    }
}