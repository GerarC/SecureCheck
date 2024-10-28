package co.edu.udea.securecheck.domain.api;

import co.edu.udea.securecheck.domain.model.form.AuditForm;

public interface AuditFormServicePort {
    AuditForm getCurrentForm(String companyId);
}
