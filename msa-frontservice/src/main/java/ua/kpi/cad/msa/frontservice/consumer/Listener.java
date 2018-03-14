package ua.kpi.cad.msa.frontservice.consumer;

import lombok.Setter;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.CountDownLatch;

public class Listener {
    private final CountDownLatch countDownLatch = new CountDownLatch(1);

    @Setter
    private DeferredResult<String> result;

    @KafkaListener(id = "foo", topics = "encryption_topic")
    public void listen(ConsumerRecord<?, ?> record) {
        System.out.println(record);
        result.setResult((String) record.value());
        countDownLatch.countDown();
    }
}
