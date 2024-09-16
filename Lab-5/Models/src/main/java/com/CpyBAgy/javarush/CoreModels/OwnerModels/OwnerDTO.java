package com.CpyBAgy.javarush.CoreModels.OwnerModels;

import com.CpyBAgy.javarush.DataAccessModels.Owner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public record OwnerDTO(Integer id, String name, Date birthday, List<Integer> cats) {
    public Owner toEntity() {
        Owner owner = new Owner();
        owner.setId(id);
        owner.setName(name);
        owner.setBirthday(birthday);
        owner.setCats(new ArrayList<>());
        return owner;
    }
}
