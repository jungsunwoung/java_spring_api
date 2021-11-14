package com.example.jungsunwoung.service.calcualation;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class Calculator {

    public CalculationResult calculate(String expression) {

        double beforeTime = System.currentTimeMillis();

        Boolean isAnswer = true;

        String answer = "";

        String str = expression;
        String[] strAry = str.split("=");

        String infixExp = strAry[0];
        Calc c = new Calc();
        if (!c.bracketsBalance(infixExp)){
            System.out.println("괄호를 정확히 하세요~");
            System.exit(1);
        }
        String postfixExp = c.postfix(infixExp);
        Double result = Math.round((c.result(postfixExp))*100)/100.0;

        String compare = Double.toString(result);

        if(compare.equals(strAry[1])){
            isAnswer=true;
        }else{
            isAnswer=false;
            answer = strAry[0] + "=" + result;
        }

        double afterTime = System.currentTimeMillis();
        double secDiffTime = (afterTime - beforeTime)/1000;

        CalculationResult build = CalculationResult.builder()
                .answer(answer).duration(secDiffTime).isAnswer(isAnswer).expression(expression).build();
        return build;
    }
}

