package com.CpyBAgy.javarush.Service.Filtering;


import com.CpyBAgy.javarush.CoreModels.OtherModels.Filter;
import com.CpyBAgy.javarush.DataAccessModels.Owner;

import java.util.List;

public class OwnerSpecification extends Specification<Owner> {
    public OwnerSpecification(List<Filter> filters) {
        super(filters);
    }
}
