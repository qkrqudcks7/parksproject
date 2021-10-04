package com.example.parksproject.service;

import com.example.parksproject.domain.ChatRoom;
import com.example.parksproject.domain.Message;
import com.example.parksproject.domain.Study;
import com.example.parksproject.domain.User;
import com.example.parksproject.payload.ChatRoomRequest;
import com.example.parksproject.payload.MessageResponse;
import com.example.parksproject.repository.ChatRoomRepository;
import com.example.parksproject.repository.StudyRepository;
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
    private final StudyRepository studyRepository;

    public ResponseEntity<?> createRoom(ChatRoomRequest chatRoomRequest, UserPrincipal userPrincipal) {

        User u = userRepository.findById(userPrincipal.getId()).get();
        Study s = studyRepository.findById(chatRoomRequest.getStudyId()).get();

        ChatRoom chatRoom = ChatRoom.builder()
                .title(chatRoomRequest.getTitle())
                .user(u)
                .study(s).build();

        chatRoomRepository.save(chatRoom);

        return new ResponseEntity<>("채팅방 생성완료", HttpStatus.OK);
    }

    public ResponseEntity<?> allRoom(Long id) {
        List<ChatRoom> all = chatRoomRepository.findByStudyId(id);

        List<ChatRoomRequest> collect = all.stream().map(chatRoom -> new ChatRoomRequest(chatRoom.getId(),chatRoom.getTitle(), chatRoom.getUser().getName(), chatRoom.getStudy().getId())).collect(Collectors.toList());

        return new ResponseEntity<>(collect,HttpStatus.OK);
    }

    public ResponseEntity<?> getRoom(Long id) {

        ChatRoom chatRoom = chatRoomRepository.findById(id).get();
        ChatRoomRequest chatRoomRequest = new ChatRoomRequest(chatRoom.getId(),chatRoom.getTitle(),chatRoom.getUser().getName(),chatRoom.getStudy().getId());

        return new ResponseEntity<>(chatRoomRequest,HttpStatus.OK);
    }

    public ResponseEntity<?> getMessage(Long id) {
        ChatRoom chatRoom = chatRoomRepository.findById(id).get();
        List<Message> messages = chatRoom.getMessages();
        List<MessageResponse> collect = messages.stream().map(message -> new MessageResponse(message.getContent(), message.getUser().getName(), message.getUser().getId(), message.getChatRoom().getId())).collect(Collectors.toList());

        return new ResponseEntity<>(collect,HttpStatus.OK);
    }
}
