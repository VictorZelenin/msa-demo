package ua.kpi.cad.msa.encryptionservice.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import ua.kpi.cad.msa.encryptionservice.algo.EncryptionWorker;
import ua.kpi.cad.msa.encryptionservice.producer.Sender;

import java.util.concurrent.CountDownLatch;

public class Listener {
    private final CountDownLatch countDownLatch = new CountDownLatch(1);

    @Autowired
    private EncryptionWorker encryptionWorker;

    @Autowired
    private Sender sender;

    @KafkaListener(id = "bar", topics = "test_topic")
    public void listen(ConsumerRecord<?, ?> record) {
        String value = (String) record.value();

        String res = encryptionWorker.encrypt(value);

        sender.send("encryption_topic", res);

        countDownLatch.countDown();
    }
}
