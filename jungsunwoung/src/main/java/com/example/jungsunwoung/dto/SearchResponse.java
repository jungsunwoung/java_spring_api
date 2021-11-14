package com.example.jungsunwoung.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchResponse {

    private String expression;

    private Boolean isAnswer;

    public SearchResponse(String expression, Boolean isAnswer) {
        this.expression = expression;
        this.isAnswer = isAnswer;
    }
}
