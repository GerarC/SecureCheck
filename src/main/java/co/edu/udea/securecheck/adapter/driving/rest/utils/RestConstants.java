package co.edu.udea.securecheck.adapter.driving.rest.utils;

public class RestConstants {
    private RestConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String CODE_OK = "200";
    public static final String CODE_CREATED = "201";
    public static final String CODE_ACCEPTED = "202";
    public static final String CODE_BAD_REQUEST = "400";
    public static final String CODE_NOT_FOUND= "404";
    public static final String CODE_CONFLICT = "409";
    public static final String CODE_UNAUTHORIZED = "401";

    public static final String SWAGGER_VALIDATIONS_FAILED = "Validations have failed";

    // Authentication
    public static final String SWAGGER_REGISTER_AUDITOR_SUMMARY = "Register a new auditor";
    public static final String SWAGGER_REGISTER_AUDITOR_SUCCESSFUL = "Auditor has been registered successfully";
    public static final String SWAGGER_REGISTER_AUDITOR_CONFLICT = "There's a conflict with given information, i.e., email or document is already registered, or User is under aged";
    public static final String SWAGGER_LOGIN_SUMMARY = "Search in data base if user exists and then return a token";
    public static final String SWAGGER_LOGIN_SUCCESSFUL = "User has been logged in successfully";
    public static final String SWAGGER_LOGIN_BAD_CREDENTIALS = "Bad credentials";
    public static final String SWAGGER_VALIDATE_TOKEN_SUMMARY = "Checks if token is valid";
    public static final String SWAGGER_VALIDATE_TOKEN_SUCCESSFUL = "Token has been validated successfully";
    public static final String SWAGGER_VALIDATE_TOKEN_EXPIRED_TOKEN = "Token has expired";
    public static final String SWAGGER_VALIDATE_TOKEN_INVALID = "Token is invalid";

    //User
    public static final String SWAGGER_GET_USER_COMPANIES_SUMMARY = "Get the user registered companies";
    public static final String SWAGGER_GET_USER_COMPANIES_SUCCESSFUL = "Companies created by the user";
    public static final String SWAGGER_USER_NOT_FOUND = "No user with that id has been found";

    // Domain
    public static final String SWAGGER_GET_ALL_DOMAIN_SUMMARY = "Return a domain list";
    public static final String SWAGGER_GET_ALL_DOMAIN_SUCCESSFUL = "A list with all domains";
    public static final String SWAGGER_GET_DOMAIN_CONTROLS_SUMMARY = "Search controls of a domain";
    public static final String SWAGGER_GET_DOMAIN_CONTROLS_SUCCESSFUL = "A list with the found controls";

    // Control
    public static final String SWAGGER_GET_ALL_CONTROL_SUMMARY = "Return a control page, depending on the query";
    public static final String SWAGGER_GET_ALL_CONTROL_SUCCESSFUL = "A bunch of controls controls";

    // Company
    public static final String SWAGGER_CREATE_COMPANY_SUMMARY = "Creates a company";
    public static final String SWAGGER_CREATE_COMPANY_SUCCESSFUL = "company is created";
    public static final String SWAGGER_GET_COMPANY_SUMMARY = "Search a company with the given id";
    public static final String SWAGGER_GET_COMPANY_SUCCESSFUL = "return that company";
    public static final String SWAGGER_DELETE_COMPANY_SUMMARY = "Deletes a company";
    public static final String SWAGGER_DELETE_COMPANY_SUCCESSFUL = "company is deleted";
    public static final String SWAGGER_GET_COMPANY_QUESTIONS_SUMMARY = "Get company questions";
    public static final String SWAGGER_GET_COMPANY_QUESTIONS_SUCCESSFUL = "A list with found questions";

    // Question
    public static final String SWAGGER_CREATE_QUESTION_SUMMARY = "creates a new custom question";
    public static final String SWAGGER_CREATE_QUESTION_SUCCESSFUL = "question is created";
    public static final String SWAGGER_DELETE_QUESTION_SUMMARY = "Deletes a question";
    public static final String SWAGGER_DELETE_QUESTION_SUCCESSFUL = "question is deleted";
    public static final String SWAGGER_UPDATE_QUESTION_SUMMARY = "Updates a question";
    public static final String SWAGGER_UPDATE_QUESTION_SUCCESSFUL = "question is updated";

    // Audit
    public static final String SWAGGER_CREATE_AUDIT_SUMMARY = "create a new audit";
    public static final String SWAGGER_CREATE_AUDIT_SUCCESSFUL = "audit has been created";
    public static final String SWAGGER_CREATE_AUDIT_COMPANY_NOT_FOUND = "required company hasn't been found";
    public static final String SWAGGER_CREATE_AUDIT_COMPANY_ALREADY_HAS_AN_ACTIVE_ONE = "company already has an active audit";

    // AuditForm
    public static final String SWAGGER_GET_AUDIT_FORM_SUMMARY = "Get and Audit in form format";
    public static final String SWAGGER_GET_AUDIT_FORM_SUCCESSFUL = "Audit form has been created";
    public static final String SWAGGER_GET_AUDIT_FORM_NOT_ACTIVE_AUDIT = "Company has not active audit";

    // Answer
    public static final String SWAGGER_UPDATE_ANSWERS_SUMMARY = "Updates a batch of answers";
    public static final String SWAGGER_UPDATE_ANSWERS_SUCCESSFUL = "Answers has been updated";

    // Some Didn't find messages
    public static final String SWAGGER_CONTROL_NOT_FOUND = "Control not found";
    public static final String SWAGGER_COMPANY_NOT_FOUND = "Company not found";
    public static final String SWAGGER_DOMAIN_NOT_FOUND = "Domain not found";
    public static final String SWAGGER_QUESTION_NOT_FOUND = "Question not found";

    public static final String SWAGGER_HOME_END_POINT = "Endpoint to test if API is working";

}
