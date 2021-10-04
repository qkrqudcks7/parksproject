package com.example.parksproject.controller;

import com.example.parksproject.payload.MessageResponse;
import com.example.parksproject.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MessageController {

    private final SimpMessagingTemplate template;

    private final MessageService messageService;


    @MessageMapping("/message")
    public void sendMessage(@Payload MessageResponse chatMessage) {
        log.info("전달 메세지: " + chatMessage);

        messageService.insertMessage(chatMessage);
        template.convertAndSend("/sub/" + chatMessage.getRoomId(), chatMessage);
    }
}
