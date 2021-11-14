package com.example.jungsunwoung.service.calcualation;

import com.example.jungsunwoung.dto.CalculationRequest;
import com.example.jungsunwoung.dto.CalculationResponse;
import com.example.jungsunwoung.entity.CalculationHistory;
import com.example.jungsunwoung.entity.User;
import com.example.jungsunwoung.repository.CalculationHistoryRepository;
import com.example.jungsunwoung.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CalculationService {

    private final CalculationHistoryRepository calculationHistoryRepository;

    private final UserRepository userRepository;

    @Transactional
    public CalculationResponse checkAnswer(CalculationRequest calculationRequest) {
        //1. 유저 처리 - 있으면 가져오고, 없으면 새로 생성
        User user = getUser(calculationRequest);
        //2. 수식 처리 - 수식을 파싱하고, 계산해야됨
        CalculationResult calculationResult = calculateExpression(calculationRequest);
        //3. 디비 저장 - 유저, 수식 저장
        saveResult(user, calculationResult);
        //4. 응답 생성
        CalculationResponse calculationResponse = new CalculationResponse(calculationResult);
        return calculationResponse;
    }

    private User getUser(CalculationRequest calculationRequest) {
        String userId = calculationRequest.getUserId();
        User user = userRepository.findById(userId).orElse(new User(userId));
        return user;
    }

    private CalculationResult calculateExpression(CalculationRequest calculationRequest) {
        Calculator calculator = new Calculator();
        CalculationResult calculationResult = calculator.calculate(calculationRequest.getExpression());
        return calculationResult;
    }

    private void saveResult(User user, CalculationResult calculationResult) {
        CalculationHistory calculationHistory = new CalculationHistory(calculationResult, user);
        userRepository.save(user);
        calculationHistoryRepository.save(calculationHistory);
    }
}
