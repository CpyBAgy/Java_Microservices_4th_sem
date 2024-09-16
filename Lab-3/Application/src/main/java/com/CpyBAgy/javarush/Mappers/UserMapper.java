package com.CpyBAgy.javarush.Mappers;

import com.CpyBAgy.javarush.DTO.UserDTO;
import com.CpyBAgy.javarush.Entities.Cat;
import com.CpyBAgy.javarush.Entities.User;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.stream.Collectors;

@NoArgsConstructor
public final class UserMapper {
    public static UserDTO map(User owner) {
        return new UserDTO(
                owner.getId(),
                owner.getName(),
                owner.getBirthday(),
                (owner.getCats() != null) ?
                        owner.getCats().stream().map(Cat::getId).collect(Collectors.toList()) :
                        new ArrayList<>());
    }
}
