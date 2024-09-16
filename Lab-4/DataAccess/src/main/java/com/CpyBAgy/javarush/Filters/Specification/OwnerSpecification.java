package com.CpyBAgy.javarush.Filters.Specification;

import com.CpyBAgy.javarush.Entities.Owner;
import com.CpyBAgy.javarush.Filters.Filter;

import java.util.List;

public class OwnerSpecification extends Specification<Owner> {
    public OwnerSpecification(List<Filter> filters) {
        super(filters);
    }
}
