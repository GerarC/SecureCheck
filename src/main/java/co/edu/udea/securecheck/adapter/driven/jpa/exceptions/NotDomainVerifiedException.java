package co.edu.udea.securecheck.adapter.driven.jpa.exceptions;

import co.edu.udea.securecheck.adapter.driven.jpa.utils.JpaConstants;

public class NotDomainVerifiedException extends RuntimeException{
    public NotDomainVerifiedException(){
        super(JpaConstants.ENTITY_SHOULD_BE_VERIFIED_IN_DOMAIN_MESSAGE);
    }
}
