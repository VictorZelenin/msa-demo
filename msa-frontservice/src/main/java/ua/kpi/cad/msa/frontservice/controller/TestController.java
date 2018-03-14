package ua.kpi.cad.msa.frontservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import ua.kpi.cad.msa.frontservice.consumer.Listener;
import ua.kpi.cad.msa.frontservice.producer.Sender;

import java.util.Random;

@RestController
public class TestController {
    private final Sender sender;
    private final Listener listener;

    @Autowired
    public TestController(Sender sender, Listener listener) {
        this.sender = sender;
        this.listener = listener;
    }

    @GetMapping("/test")
    public DeferredResult<String> test() {
        DeferredResult<String> result = new DeferredResult<>();
        listener.setResult(result);
        String payload = String.valueOf(new Random().nextInt());
        sender.send("test_topic", payload);

        return result;
    }
}
