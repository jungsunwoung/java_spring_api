package com.example.jungsunwoung.controller;

import com.example.jungsunwoung.dto.CalculationRequest;
import com.example.jungsunwoung.dto.CalculationResponse;
import com.example.jungsunwoung.dto.SearchResponse;
import com.example.jungsunwoung.entity.User;
import com.example.jungsunwoung.service.calcualation.CalculationService;
import com.example.jungsunwoung.service.search.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1", produces = "application/json; charset=UTF8")
@RequiredArgsConstructor
public class DbController {

    private final CalculationService calculationService;

    private final SearchService searchService;

    @PostMapping("/calculate")
    public ResponseEntity<CalculationResponse> postUserCalculation(@RequestBody CalculationRequest calculationRequest){
        CalculationResponse calculationResponse = calculationService.checkAnswer(calculationRequest);
        return new ResponseEntity<CalculationResponse>(calculationResponse, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<SearchResponse>> search(@RequestParam String userId,
                                                 @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
                                                 @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end) {
        List<SearchResponse> searchResponse = searchService.search(userId, start, end);
        return new ResponseEntity<>(searchResponse, HttpStatus.OK);
    }
}
