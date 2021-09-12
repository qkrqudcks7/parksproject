package com.example.parksproject.payload;

import com.example.parksproject.domain.ApplyState;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApplyStudyResponse {

    private Long id;

    private ApplyState applyState;

    private Long userId;

    private String message;

}
