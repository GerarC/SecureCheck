package co.edu.udea.securecheck.domain.utils.validation;

public class ValidationUtils {
    private ValidationUtils() {
        throw new IllegalStateException("Utility class");
    }

    private static Validation buildValidation(boolean condition) {
        return Validation.builder()
                .valid(condition)
                .build();
    }

    /**
     * Receives a condition and, if that condition is not valid (false), then throws the given exception.<br/><br/>
     * When you are writing it, you could say: <i>validates if <b>condition</b> or throws <b>exception</b></i>
     *
     * @param condition any that returns true or false
     * @param exception the exception related to that condition
     * @param <X>       the type of the throwable
     * @throws X the given exception
     */
    public static <X extends Throwable> void validateOrThrow(boolean condition, X exception) throws X {
        buildValidation(condition).ifInvalidThrows(exception);
    }
}
