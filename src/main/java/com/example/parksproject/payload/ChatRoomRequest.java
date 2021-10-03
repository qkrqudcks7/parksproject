package com.example.parksproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChatRoomRequest {

    private Long id;
    private String title;
    private String name;
}
