package com.CpyBAgy.javarush.CoreModels.CatModels;


import com.CpyBAgy.javarush.CoreModels.OtherModels.Color;
import com.CpyBAgy.javarush.DataAccessModels.Cat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public record CatDTO(Integer id, String name, String breed, Color color, Date birthday, Integer owner, List<Integer> friends) {
    public Cat toEntity() {
        Cat cat = new Cat();
        cat.setId(id);
        cat.setName(name);
        cat.setBreed(breed);
        cat.setColor(color);
        cat.setBirthday(birthday);
        cat.setOwner(null);
        cat.setFriends(new ArrayList<>());
        return cat;
    }
}
