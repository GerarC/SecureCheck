package co.edu.udea.securecheck.domain.exceptions;

import co.edu.udea.securecheck.domain.utils.Constants;

public class CompanyHasNotActiveAuditException extends RuntimeException {
    public CompanyHasNotActiveAuditException(String companyId) {
        super(String.format(Constants.COMPANY_HAS_NOT_ACTIVE_AUDIT_MESSAGE, companyId));
    }
}
