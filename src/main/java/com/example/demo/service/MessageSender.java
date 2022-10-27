package com.example.demo.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MessageSender {

    private static final String TOPIC = "example";

    KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String msg) {
        ListenableFuture<SendResult<String, String>> future =
            kafkaTemplate.send(TOPIC, msg);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Sent message=[" + msg +
                    "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println(
                    "Unable to send message=[" + msg + "] due to : " + ex.getMessage());
            }
        });
    }

}
