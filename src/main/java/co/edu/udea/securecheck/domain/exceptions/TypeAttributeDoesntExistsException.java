package co.edu.udea.securecheck.domain.exceptions;

import co.edu.udea.securecheck.domain.utils.Constants;

public class TypeAttributeDoesntExistsException extends RuntimeException {
    public TypeAttributeDoesntExistsException(String attribute, String className) {
        super(String.format(Constants.TYPE_ATTRIBUTE_DOESNT_EXISTS_MESSAGE, attribute, className));
    }
}
