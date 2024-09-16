package com.CpyBAgy.javarush.Service.Filtering;

import com.CpyBAgy.javarush.CoreModels.OtherModels.Filter;
import com.CpyBAgy.javarush.CoreModels.OtherModels.FilteringType;

import java.util.ArrayList;
import java.util.List;

public class OwnerFilterManager {
    public List<Filter> manageFilters(String name, FilteringType filteringType) {
        List<Filter> filters = new ArrayList<>();

        if (name != null) {
            filters.add(new Filter("name", name, filteringType));
        }

        return filters;
    }
}
