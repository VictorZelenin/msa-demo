package ua.kpi.cad.msa.frontservice.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Sender {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public Sender(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String topic, String payload) {
        kafkaTemplate.send(topic, payload);
    }
}
