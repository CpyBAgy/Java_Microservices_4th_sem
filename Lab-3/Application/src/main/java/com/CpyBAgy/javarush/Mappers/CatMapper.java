package com.CpyBAgy.javarush.Mappers;

import com.CpyBAgy.javarush.DTO.CatDTO;
import com.CpyBAgy.javarush.Entities.Cat;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.stream.Collectors;

@NoArgsConstructor
public final class CatMapper {
    public static CatDTO map(Cat cat) {
        return new CatDTO(
                cat.getId(),
                cat.getName(),
                cat.getBreed(),
                cat.getColor(),
                cat.getBirthday(),
                (cat.getUser() != null) ? cat.getUser().getId() : 0,
                (cat.getFriends() != null) ?
                        cat.getFriends().stream().map(Cat::getId).collect(Collectors.toList()) :
                        new ArrayList<>());
    }
}
