package com.CpyBAgy.javarush.Mappers;

import com.CpyBAgy.javarush.DTO.OwnerDTO;
import com.CpyBAgy.javarush.Entities.Cat;
import com.CpyBAgy.javarush.Entities.Owner;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.stream.Collectors;

@NoArgsConstructor
public final class OwnerMapper {
    public OwnerDTO map(Owner owner) {
        return new OwnerDTO(
                owner.getId(),
                owner.getName(),
                owner.getBirthday(),
                (owner.getCats() != null) ?
                        owner.getCats().stream().map(Cat::getId).collect(Collectors.toList()) :
                        new ArrayList<>());
    }
}
