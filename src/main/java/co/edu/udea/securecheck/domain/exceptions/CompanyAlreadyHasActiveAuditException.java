package co.edu.udea.securecheck.domain.exceptions;

import co.edu.udea.securecheck.domain.utils.Constants;

public class CompanyAlreadyHasActiveAuditException extends RuntimeException {
    public CompanyAlreadyHasActiveAuditException(String companyId) {
        super(String.format(Constants.COMPANY_ALREADY_HAS_ACTIVE_AUDIT_MESSAGE, companyId));
    }
}
