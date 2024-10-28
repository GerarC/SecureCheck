package co.edu.udea.securecheck.adapter.driven.jpa.specification;

import co.edu.udea.securecheck.adapter.driven.jpa.entity.CompanyEntity;
import co.edu.udea.securecheck.domain.utils.filters.CompanyFilter;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class CompanySpecification {
    private static final String USER_ID = "id";
    private static final String USER = "user";
    private static final String NIT = "nit";
    private static final String NAME = "name";
    private static final String NICK = "nick";
    private static final String CREATED_AT = "createdAt";


    private CompanySpecification() {
        throw new IllegalStateException("Utility class");
    }

    public static Specification<CompanyEntity> filterBy(CompanyFilter filter) {
        if (filter == null) return null;
        return Specification.where(hasNit(filter.getNit()))
                .and(hasName(filter.getName()))
                .and(hasNick(filter.getNick()))
                .and(isCreatedBefore(filter.getCreatedBefore()))
                .and(isCreatedAfter(filter.getCreatedAfter()));
    }

    public static Specification<CompanyEntity> filterBy(String userId, CompanyFilter filter) {
        Specification<CompanyEntity> specs = Specification.where(hasUserId(userId));
        if (filter == null) return specs;
        return specs.and(hasNit(filter.getNit()))
                .and(hasName(filter.getName()))
                .and(hasNick(filter.getNick()))
                .and(isCreatedBefore(filter.getCreatedBefore()))
                .and(isCreatedAfter(filter.getCreatedAfter()));
    }

    private static Specification<CompanyEntity> hasUserId(String userId) {
        return (root, query, criteriaBuilder) ->
                userId == null || userId.isEmpty()
                        ? criteriaBuilder.conjunction()
                        : criteriaBuilder
                        .equal(root.join(USER).get(USER_ID), userId);
    }

    private static Specification<CompanyEntity> hasNit(String nit) {
        return (root, query, criteriaBuilder) ->
                nit == null || nit.isEmpty()
                        ? criteriaBuilder.conjunction()
                        : criteriaBuilder
                        .like(root.get(NIT), "%" + nit + "%");
    }

    private static Specification<CompanyEntity> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                name == null || name.isEmpty()
                        ? criteriaBuilder.conjunction()
                        : criteriaBuilder
                        .like(root.get(NAME), "%" + name + "%");
    }

    private static Specification<CompanyEntity> hasNick(String nick) {
        return (root, query, criteriaBuilder) ->
                nick == null || nick.isEmpty()
                        ? criteriaBuilder.conjunction()
                        : criteriaBuilder
                        .like(root.get(NICK), "%" + nick + "%");
    }

    private static Specification<CompanyEntity> isCreatedBefore(LocalDateTime createdBefore) {
        return (root, query, criteriaBuilder) ->
                createdBefore == null
                        ? criteriaBuilder.conjunction()
                        : criteriaBuilder.lessThanOrEqualTo(root.get(CREATED_AT), createdBefore);
    }

    private static Specification<CompanyEntity> isCreatedAfter(LocalDateTime createdAfter) {
        return (root, query, criteriaBuilder) ->
                createdAfter == null
                        ? criteriaBuilder.conjunction()
                        : criteriaBuilder.greaterThanOrEqualTo(root.get(CREATED_AT), createdAfter);
    }
}
