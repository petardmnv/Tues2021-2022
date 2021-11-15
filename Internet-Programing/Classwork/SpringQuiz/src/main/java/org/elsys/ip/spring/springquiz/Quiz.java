package org.elsys.ip.spring.springquiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Component
public class Quiz {
    private ArrayList<String> potentialAnswers = new ArrayList<>();
    private Map<String, Character> questions = new HashMap<>();

    public Quiz(ArrayList<String> potentialAnswers, Map<String, Character> questions) {
        this.potentialAnswers = potentialAnswers;
        this.questions = questions;
    }

    public String startQuiz(){
        for (String question: this.questions.keySet()) {
            Scanner sc = new Scanner(System.in);
            String answer = String.valueOf(this.questions.get(question));
            if (!(sc.nextLine().equals(answer))){
                return "Wrong answer";
            }
        }
        return "No more questions";
    }

}
