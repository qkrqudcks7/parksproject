package com.example.parksproject.controller;

import com.example.parksproject.payload.ChatRoomRequest;
import com.example.parksproject.security.CurrentUser;
import com.example.parksproject.security.UserPrincipal;
import com.example.parksproject.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @GetMapping("/chatroom")
    public ResponseEntity<?> allRoom() {
        return chatRoomService.allRoom();
    }

    @PostMapping("/chatroom")
    public ResponseEntity<?> createRoom(@CurrentUser UserPrincipal userPrincipal,
                                        @RequestBody ChatRoomRequest chatRoomRequest) {

        return chatRoomService.createRoom(chatRoomRequest,userPrincipal);
    }

    @GetMapping("/chatroom/{id}")
    public ResponseEntity<?> room(@PathVariable Long id) {
        return chatRoomService.getRoom(id);
    }
}
