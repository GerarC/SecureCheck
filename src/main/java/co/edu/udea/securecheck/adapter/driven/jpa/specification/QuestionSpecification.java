package co.edu.udea.securecheck.adapter.driven.jpa.specification;

import co.edu.udea.securecheck.adapter.driven.jpa.entity.CustomQuestionEntity;
import co.edu.udea.securecheck.domain.utils.filters.QuestionFilter;
import org.springframework.data.jpa.domain.Specification;

public class QuestionSpecification {
    private static final String COMPANY_ID = "id";
    private static final String CONTROL_ID = "id";
    private static final String BODY = "body";
    private static final String COMPANY = "company";
    private static final String CONTROL = "control";


    private QuestionSpecification() {
        throw new IllegalStateException("Utility class");
    }

    public static Specification<CustomQuestionEntity> filterBy(QuestionFilter filter) {
        if (filter == null) return null;
        return Specification.where(hasCompanyId(filter.getCompanyId()))
                .and(hasControlId(filter.getControlId()))
                .and(hasBody(filter.getBody()));
    }

    private static Specification<CustomQuestionEntity> hasCompanyId(String companyId) {
        return (root, query, criteriaBuilder) ->
                companyId == null || companyId.isEmpty()
                        ? criteriaBuilder.conjunction()
                        : criteriaBuilder
                        .equal(root.join(COMPANY).get(COMPANY_ID), companyId);
    }

    private static Specification<CustomQuestionEntity> hasControlId(Long controlId) {
        return (root, query, criteriaBuilder) ->
                controlId == null
                        ? criteriaBuilder.conjunction()
                        : criteriaBuilder
                        .equal(root.join(CONTROL).get(CONTROL_ID), controlId);
    }

    private static Specification<CustomQuestionEntity> hasBody(String body) {
        return (root, query, criteriaBuilder) ->
                body == null || body.isEmpty()
                        ? criteriaBuilder.conjunction()
                        : criteriaBuilder
                        .like(root.get(BODY), "%" + body + "%");
    }
}
