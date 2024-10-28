package co.edu.udea.securecheck.domain.exceptions;

import co.edu.udea.securecheck.domain.utils.Constants;

public class MaxQuestionReachedException extends RuntimeException {
    public MaxQuestionReachedException() {
        super(Constants.MAX_CONTROL_QUESTIONS_REACHED_MESSAGE);
    }
}
