package com.example.parksproject.service;

import com.example.parksproject.domain.ChatRoom;
import com.example.parksproject.domain.Message;
import com.example.parksproject.domain.User;
import com.example.parksproject.payload.MessageResponse;
import com.example.parksproject.repository.ChatRoomRepository;
import com.example.parksproject.repository.MessageRepository;
import com.example.parksproject.repository.UserRepository;
import com.example.parksproject.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    private final UserRepository userRepository;

    private final ChatRoomRepository chatRoomRepository;

    public void insertMessage(MessageResponse chatMessage) {
        User u = userRepository.findById(chatMessage.getUserId()).get();
        ChatRoom c = chatRoomRepository.findById(chatMessage.getRoomId()).get();

        Message message = Message.builder()
                .content(chatMessage.getContent())
                .user(u)
                .chatRoom(c).build();

        messageRepository.save(message);
    }
}
