package com.example.parksproject.service;

import com.example.parksproject.domain.ChatRoom;
import com.example.parksproject.domain.User;
import com.example.parksproject.payload.ChatRoomRequest;
import com.example.parksproject.repository.ChatRoomRepository;
import com.example.parksproject.repository.UserRepository;
import com.example.parksproject.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    public ResponseEntity<?> createRoom(ChatRoomRequest chatRoomRequest, UserPrincipal userPrincipal) {

        User u = userRepository.findById(userPrincipal.getId()).get();

        ChatRoom chatRoom = ChatRoom.builder()
                .title(chatRoomRequest.getTitle())
                .user(u).build();

        chatRoomRepository.save(chatRoom);

        return new ResponseEntity<>("채팅방 생성완료", HttpStatus.OK);
    }

    public ResponseEntity<?> allRoom() {
        List<ChatRoom> all = chatRoomRepository.findAll();

        List<ChatRoomRequest> collect = all.stream().map(chatRoom -> new ChatRoomRequest(chatRoom.getId(),chatRoom.getTitle(), chatRoom.getUser().getName())).collect(Collectors.toList());

        return new ResponseEntity<>(collect,HttpStatus.OK);
    }

    public ResponseEntity<?> getRoom(Long id) {

        ChatRoom chatRoom = chatRoomRepository.findById(id).get();
        ChatRoomRequest chatRoomRequest = new ChatRoomRequest(chatRoom.getId(),chatRoom.getTitle(),chatRoom.getUser().getName());

        return new ResponseEntity<>(chatRoomRequest,HttpStatus.OK);
    }
}
