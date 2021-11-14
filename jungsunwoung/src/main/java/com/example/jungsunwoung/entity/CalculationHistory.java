package com.example.jungsunwoung.entity;

import com.example.jungsunwoung.service.calcualation.CalculationResult;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class CalculationHistory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String expression;

    @CreatedDate
    private LocalDateTime timeStamp;

    private Boolean isAnswer;

    private Double duration;

    private String answer;

    public CalculationHistory(CalculationResult calculationResult, User user) {
        this.expression = calculationResult.getExpression();
        this.isAnswer = calculationResult.getIsAnswer();
        this.duration = calculationResult.getDuration();
        this.answer = calculationResult.getAnswer();
        this.setUser(user);
    }

    private void setUser(User user) {
        //user setter는 private으로 선언해 외부에서 사용될 수 없도록 한다. 이름도 변경 필요
        this.user = user;
        user.addCalculationHistory(this);
    }
}
