package com.example.parksproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageResponse {

    private String content;

    private String name;

    private Long userId;

    private Long roomId;
}
