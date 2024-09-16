package com.CpyBAgy.javarush.Filters;

import com.CpyBAgy.javarush.Entities.Color;

import java.util.ArrayList;
import java.util.List;

public class CatFilterManager {
    public static List<Filter> manage(String name, Color color, String breed, FilteringType filteringType) {
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
