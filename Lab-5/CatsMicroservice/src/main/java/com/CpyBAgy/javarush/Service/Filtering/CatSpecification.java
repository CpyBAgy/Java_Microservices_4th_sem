package com.CpyBAgy.javarush.Service.Filtering;

import com.CpyBAgy.javarush.CoreModels.OtherModels.Filter;
import com.CpyBAgy.javarush.DataAccessModels.Cat;

import java.util.List;

public class CatSpecification extends Specification<Cat> {
    public CatSpecification(List<Filter> filters) {
        super(filters);
    }
}
