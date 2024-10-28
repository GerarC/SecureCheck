package co.edu.udea.securecheck.domain.exceptions;

import co.edu.udea.securecheck.domain.utils.Constants;

public class MinQuestionReachedException extends RuntimeException {
    public MinQuestionReachedException() {
        super(Constants.MIN_CONTROL_QUESTIONS_REACHED_MESSAGE);
    }
}
