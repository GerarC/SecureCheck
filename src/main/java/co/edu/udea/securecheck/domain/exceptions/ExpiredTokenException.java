package co.edu.udea.securecheck.domain.exceptions;

import co.edu.udea.securecheck.domain.utils.Constants;

public class ExpiredTokenException extends RuntimeException {
    public ExpiredTokenException() {
        super(Constants.EXPIRED_TOKEN_MESSAGE);
    }
}
