package com.CpyBAgy.javarush.Filters.Specification;

import com.CpyBAgy.javarush.Entities.Cat;
import com.CpyBAgy.javarush.Filters.Filter;

import java.util.List;

public class CatSpecification extends Specification<Cat> {
    public CatSpecification(List<Filter> filters) {
        super(filters);
    }
}
