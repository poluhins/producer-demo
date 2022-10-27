package com.example.demo.controller;

import com.example.demo.service.MessageSender;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/send")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SendController {

    MessageSender messageSender;

    @GetMapping
    public ResponseEntity<String> sendMessage(@RequestParam String message) {
        messageSender.sendMessage(message);
        return ResponseEntity.ok(message);
    }

}
