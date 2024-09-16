package com.CpyBAgy.javarush.Service.Filtering;

import com.CpyBAgy.javarush.CoreModels.OtherModels.Filter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public abstract class Specification<Type> implements org.springframework.data.jpa.domain.Specification<Type> {
    private final List<Filter> filters;

    public Specification(List<Filter> filters) {
        this.filters = filters;
    }

    @Override
    public Predicate toPredicate(@NonNull Root<Type> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        for (Filter filter : filters) {
            switch (filter.filteringType()) {
                case EQUALS -> predicates.add(criteriaBuilder.equal(root.get(filter.attribute()), filter.value()));
                case LIKE -> predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(filter.attribute())), "%" + filter.value() + "%"));
            }
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
