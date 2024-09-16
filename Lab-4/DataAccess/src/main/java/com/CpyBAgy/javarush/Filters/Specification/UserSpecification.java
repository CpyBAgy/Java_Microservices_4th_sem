package com.CpyBAgy.javarush.Filters.Specification;

import com.CpyBAgy.javarush.Entities.User;
import com.CpyBAgy.javarush.Filters.Filter;

import java.util.List;

public class UserSpecification extends Specification<User> {
    public UserSpecification(List<Filter> filters) {
        super(filters);
    }
}
