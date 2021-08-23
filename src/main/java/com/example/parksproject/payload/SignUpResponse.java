package com.example.parksproject.payload;

import lombok.Data;

@Data
public class SignUpResponse {
    private boolean success;
    private String message;

    public SignUpResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
