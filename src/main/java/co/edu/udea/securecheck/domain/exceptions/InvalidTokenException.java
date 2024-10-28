package co.edu.udea.securecheck.domain.exceptions;

import co.edu.udea.securecheck.domain.utils.Constants;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException() {
        super(Constants.INVALID_TOKEN_MESSAGE);
    }
}
