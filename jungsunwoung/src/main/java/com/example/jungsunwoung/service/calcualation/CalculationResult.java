package com.example.jungsunwoung.service.calcualation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@AllArgsConstructor @Builder
public class CalculationResult {

    private String expression;

    private Boolean isAnswer;

    private Double duration;

    private String answer;
}
