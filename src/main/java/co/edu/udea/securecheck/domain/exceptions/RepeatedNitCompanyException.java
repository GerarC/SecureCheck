package co.edu.udea.securecheck.domain.exceptions;

import co.edu.udea.securecheck.domain.utils.Constants;

public class RepeatedNitCompanyException extends RuntimeException {
    public RepeatedNitCompanyException(String nit) {
        super(String.format(Constants.REPEATED_NIT_COMPANY_EXCEPTION, nit));
    }
}
