package com.CpyBAgy.javarush.Service.Filtering;

import com.CpyBAgy.javarush.CoreModels.OtherModels.Color;
import com.CpyBAgy.javarush.CoreModels.OtherModels.Filter;
import com.CpyBAgy.javarush.CoreModels.OtherModels.FilteringType;

import java.util.ArrayList;
import java.util.List;

public class CatFilterManager {
    public List<Filter> manageFilters(String name, Color color, String breed, FilteringType filteringType) {
        List<Filter> filters = new ArrayList<>();

        if (name != null) {
            filters.add(new Filter("name", name, filteringType));
        }
        if (color != null) {
            filters.add(new Filter("color", color.toString(), filteringType));
        }
        if (breed != null) {
            filters.add(new Filter("breed", breed, filteringType));
        }

        return filters;
    }
}
