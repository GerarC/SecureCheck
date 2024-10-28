package co.edu.udea.securecheck.adapter.driven.jpa.utils;

public class JpaConstants {
    private JpaConstants() {
        throw new IllegalStateException("Utility class");
    }

        public static final String ENTITY_SHOULD_BE_VERIFIED_IN_DOMAIN_MESSAGE = "Entity cannot be null because it's verified in Domain";
}
