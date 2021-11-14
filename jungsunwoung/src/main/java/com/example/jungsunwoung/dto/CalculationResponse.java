package com.example.jungsunwoung.dto;

import com.example.jungsunwoung.service.calcualation.CalculationResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CalculationResponse {

    private Boolean isAnswer;

    private String answer;

    private Double duration;

    public CalculationResponse(CalculationResult calculationResult) {
        this.isAnswer = calculationResult.getIsAnswer();
        this.answer = calculationResult.getAnswer();
        this.duration = calculationResult.getDuration();
    }
}
