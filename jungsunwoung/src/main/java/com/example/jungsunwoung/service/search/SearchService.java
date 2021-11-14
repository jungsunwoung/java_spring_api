package com.example.jungsunwoung.service.search;

import com.example.jungsunwoung.dto.SearchResponse;
import com.example.jungsunwoung.entity.CalculationHistory;
import com.example.jungsunwoung.entity.User;
import com.example.jungsunwoung.repository.CalculationHistoryRepository;
import com.example.jungsunwoung.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final UserRepository userRepository;

    public List<SearchResponse> search(String userId, LocalDateTime start, LocalDateTime end) {
        User targetUser = userRepository.findByIdAndStartAndEnd(userId, start, end);

        List<CalculationHistory> calculationHistoryList = targetUser.getCalculationHistoryList();

        List<SearchResponse> result = calculationHistoryList.stream().map((history) -> {
            return new SearchResponse(history.getExpression(), history.getIsAnswer());
        }).collect(Collectors.toList());

        return result;
    }
}
