package org.elsys.ip.springtimer;
import org.springframework.web.bind.annotation.*;

@RestController
public class  TimerController {
    public String getQuestion() {
        String state = checkState();
        if (state != null) {
            return state;
        }

        return stringBuilder.toString(bank.getQuestion());
    }

    @PostMapping("/submit/{answer}")
    public String answerQuestion(@PathVariable String answer) {
        String state = checkState();
        if (state != null) {
            return state;
        }

        int answerIndex = stringBuilder.getAnswerIndex(answer);

        if (answerIndex >= bank.getQuestion().getAnswers().size()) {
            throw new IllegalArgumentException("The question has " + bank.getQuestion().getAnswers().size() + " answers.");
        }

        if (bank.getQuestion().getAnswers().get(answerIndex).isCorrect()) {
            bank.moveToNextQuestion();
        } else {
            isGameOver = true;
        }

        return getQuestion();
    }

    private String checkState() {
        if (isGameOver) return "Game over. No more questions";

        if (bank.getQuestion() == null) return "Congrats. You finished the quiz!";

        return null;
    }
}
