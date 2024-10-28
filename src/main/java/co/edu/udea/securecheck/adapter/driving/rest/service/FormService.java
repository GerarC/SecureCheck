package co.edu.udea.securecheck.adapter.driving.rest.service;

import co.edu.udea.securecheck.adapter.driving.rest.dto.response.form.AuditFormResponse;

public interface FormService {
    AuditFormResponse getAuditForm(String id);
}
