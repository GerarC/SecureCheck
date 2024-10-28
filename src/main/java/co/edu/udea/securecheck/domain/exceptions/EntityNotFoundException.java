package co.edu.udea.securecheck.domain.exceptions;

import co.edu.udea.securecheck.domain.utils.Constants;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String className, String id) {
        super(String.format(Constants.ENTITY_NOT_FOUND_MESSAGE, className, id));
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
