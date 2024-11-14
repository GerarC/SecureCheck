package co.edu.udea.securecheck.domain.utils;

import co.edu.udea.securecheck.domain.utils.annotation.Generated;

@Generated
public class Constants {

    private Constants(){
        throw new IllegalStateException("Utility constants class");
    }

    public static final Integer MAX_QUESTION_SIZE = 255;

    // Mensaje de registro
    public static final String AUDITOR_REGISTERED_MESSAGE = "'%s %s' con el correo '%s' ha sido registrado";

    // Mensajes de campos vacíos
    public static final String EMPTY_NAME_FIELD_MESSAGE = "El campo 'nombre' no puede estar vacío";
    public static final String EMPTY_NIT_FIELD_MESSAGE = "El campo 'NIT' no puede estar vacío";
    public static final String EMPTY_LASTNAME_FIELD_MESSAGE = "El campo 'apellido' no puede estar vacío";
    public static final String EMPTY_IDENTITY_DOCUMENT_FIELD_MESSAGE = "El campo 'documento de identidad' no puede estar vacío";
    public static final String EMPTY_BIRTHDATE_FIELD_MESSAGE = "El campo 'fecha de nacimiento' no puede estar vacío";
    public static final String EMPTY_PHONE_FIELD_MESSAGE = "El campo 'teléfono' no puede estar vacío";
    public static final String EMPTY_EMAIL_FIELD_MESSAGE = "El campo 'correo electrónico' no puede estar vacío";
    public static final String EMPTY_PASSWORD_FIELD_MESSAGE = "El campo 'contraseña' no puede estar vacío";
    public static final String EMPTY_ADDRESS_FIELD_MESSAGE = "El campo 'dirección' no puede estar vacío";
    public static final String EMPTY_USER_ID_FIELD_MESSAGE = "El campo 'ID de usuario' no puede estar vacío";
    public static final String EMPTY_BODY_FIELD_MESSAGE = "El campo 'cuerpo' no puede estar vacío";
    public static final String EMPTY_CONTROL_ID_FIELD_MESSAGE = "El campo 'ID de control' no puede estar vacío";
    public static final String EMPTY_COMPANY_ID_FIELD_MESSAGE = "El campo 'ID de compañía' no puede estar vacío";

    // Expresiones regulares
    public static final String EMAIL_REGEX_RFC5322 = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*"
            + "@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    public static final String PHONE_NUMBER_REGEX = "^(\\+\\d{2})?\\d{10}$";
    public static final String IDENTITY_DOCUMENT_REGEX = "^\\d{6,16}";

    // Mensajes de límites
    public static final String PHONE_OUT_OF_BOUNDS_MESSAGE = "El número de 'teléfono' debe tener entre 10 y 14 caracteres";
    public static final String EMAIL_OUT_OF_BOUNDS_MESSAGE = "El 'correo electrónico' debe tener entre 7 y 255 caracteres";
    public static final String PASSWORD_OUT_OF_BOUNDS_MESSAGE = "La 'contraseña' debe tener entre 8 y 16 caracteres";
    public static final String IDENTITY_DOCUMENT_OUT_OF_BOUNDS_MESSAGE = "El 'documento de identidad' debe tener entre 6 y 15 caracteres";

    // Mensajes de formato no válido
    public static final String NOT_VALID_EMAIL_MESSAGE = "Correo electrónico no válido";
    public static final String NOT_VALID_PHONE_NUMBER_MESSAGE = "Número de teléfono no válido";
    public static final String NOT_NUMERIC_IDENTITY_DOCUMENT_MESSAGE = "El documento de identidad debe ser numérico";

    // Mensaje de fecha de nacimiento
    public static final String FUTURE_BIRTH_DATE_MESSAGE = "El campo 'fecha de nacimiento' no puede ser una fecha futura";

    // Mensajes de excepción
    public static final String EMAIL_ALREADY_EXISTS_MESSAGE = "Ya existe un usuario con el correo electrónico '%s'";
    public static final String IDENTITY_DOCUMENT_ALREADY_REGISTERED_MESSAGE = "Ya existe un usuario con el número de documento '%s'";
    public static final String UNDERAGE_USER_MESSAGE = "El usuario nacido el '%tF' es menor de edad";
    public static final String COMPANY_ALREADY_HAS_ACTIVE_AUDIT_MESSAGE = "La compañía '%s' ya tiene una auditoría activa";
    public static final String COMPANY_HAS_NOT_ACTIVE_AUDIT_MESSAGE = "La compañía %s no tiene una auditoría activa";
    public static final String INVALID_TOKEN_MESSAGE = "El token proporcionado es inválido";
    public static final String EXPIRED_TOKEN_MESSAGE = "El token proporcionado ha expirado";
    public static final String REPEATED_NIT_COMPANY_EXCEPTION = "Este usuario ya tiene una empresa con NIT '%s'";
    public static final String MAX_CONTROL_QUESTIONS_REACHED_MESSAGE = "Este control ya alcanzó el máximo de 3 preguntas";
    public static final String MIN_CONTROL_QUESTIONS_REACHED_MESSAGE = "Un control debe tener al menos una pregunta";

    public static final String ENTITY_NOT_FOUND_MESSAGE = "Entidad de tipo '%s' con ID '%s' no encontrada";
    public static final String TYPE_ATTRIBUTE_DOESNT_EXISTS_MESSAGE = "El atributo '%s' no existe en el tipo '%s'";
    public static final String USER_WITH_EMAIL_NOT_FOUND_MESSAGE = "Usuario con el correo '%s' no encontrado";

    public static final Integer DEFAULT_PAGE_NUMBER = 0;
    public static final Integer DEFAULT_PAGE_SIZE = 10;
    public static final String DEFAULT_SORT_COLUMN = "id";
    public static final Boolean DEFAULT_ASCENDING = false;
}
