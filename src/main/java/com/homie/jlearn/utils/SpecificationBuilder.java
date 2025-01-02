package com.homie.jlearn.utils;

import com.homie.jlearn.common.Filter;
import com.homie.jlearn.common.NumberFilter;
import com.homie.jlearn.common.StringFilter;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.data.jpa.domain.Specification;

public class SpecificationBuilder {

    public static <ENTITY, FIELD_TYPE> Specification<ENTITY> buildSpecification(
        SingularAttribute<ENTITY, FIELD_TYPE> field,
        Filter<FIELD_TYPE> filter
    ) {
        return (root, query, builder) -> {
            if (filter == null || !filter.hasValue()) {
                return null;
            }

            Predicate predicate = builder.conjunction();
            if (filter.getEq() != null) {
                predicate = builder.and(predicate, builder.equal(root.get(field), filter.getEq()));
            }
            if (filter.getNe() != null) {
                predicate = builder.and(predicate, builder.notEqual(root.get(field), filter.getNe()));
            }
            if (filter.getIn() != null && !filter.getIn().isEmpty()) {
                predicate = builder.and(predicate, root.get(field).in(filter.getIn()));
            }
            if (filter.getNin() != null && !filter.getNin().isEmpty()) {
                predicate = builder.and(predicate, builder.not(root.get(field).in(filter.getNin())));
            }
            return predicate;
        };
    }

    public static <ENTITY, FIELD_TYPE extends Comparable<? super FIELD_TYPE>> Specification<ENTITY> buildSpecification(
        SingularAttribute<ENTITY, FIELD_TYPE> field,
        NumberFilter<FIELD_TYPE> filter
    ) {
        Specification<ENTITY> baseSpec = buildSpecification(field, (Filter<FIELD_TYPE>) filter);
        return (root, query, builder) -> {
            if (filter == null || !filter.hasValue()) {
                return null;
            }

            Predicate predicate = baseSpec.toPredicate(root, query, builder);
            if (filter.getGt() != null) {
                predicate = builder.and(predicate, builder.greaterThan(root.get(field), filter.getGt()));
            }
            if (filter.getLt() != null) {
                predicate = builder.and(predicate, builder.lessThan(root.get(field), filter.getLt()));
            }
            if (filter.getGte() != null) {
                predicate = builder.and(predicate, builder.greaterThanOrEqualTo(root.get(field), filter.getGte()));
            }
            if (filter.getLte() != null) {
                predicate = builder.and(predicate, builder.lessThanOrEqualTo(root.get(field), filter.getLte()));
            }
            return predicate;
        };
    }

    public static <ENTITY> Specification<ENTITY> buildSpecification(SingularAttribute<ENTITY, String> field, StringFilter filter) {
        Specification<ENTITY> baseSpec = buildSpecification(field, (Filter<String>) filter);
        return (root, query, builder) -> {
            if (filter == null || !filter.hasValue()) {
                return null;
            }

            Predicate predicate = baseSpec.toPredicate(root, query, builder);
            if (filter.getContains() != null) {
                predicate = builder.and(predicate, builder.like(root.get(field), "%" + filter.getContains() + "%"));
            }
            if (filter.getNotContains() != null) {
                predicate = builder.and(predicate, builder.notLike(root.get(field), "%" + filter.getNotContains() + "%"));
            }
            return predicate;
        };
    }
}
