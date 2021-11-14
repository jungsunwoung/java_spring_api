package com.example.jungsunwoung.entity;

import lombok.AccessLevel;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @Column(length = 100)
    private String id;

    @OneToMany(mappedBy = "user")
    private List<CalculationHistory> calculationHistoryList = new ArrayList<>();

    public User(String id) {
        this.id = id;
    }

    public void addCalculationHistory(CalculationHistory calculationHistory) {
        this.calculationHistoryList.add(calculationHistory);
    }
}
