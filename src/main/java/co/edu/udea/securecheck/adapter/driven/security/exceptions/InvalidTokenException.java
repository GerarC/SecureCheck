package co.edu.udea.securecheck.adapter.driven.security.exceptions;

import co.edu.udea.securecheck.adapter.driven.security.utils.SecurityConstants;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException() {
        super(SecurityConstants.INVALID_TOKEN_MESSAGE);
    }
}
