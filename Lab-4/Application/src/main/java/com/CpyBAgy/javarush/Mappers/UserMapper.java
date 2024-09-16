package com.CpyBAgy.javarush.Mappers;

import com.CpyBAgy.javarush.DTO.UserDTO;
import com.CpyBAgy.javarush.Entities.User;

public class UserMapper {
    public UserDTO map(User user) {
        return new UserDTO(
                user.getId(),
                user.getLogin(),
                user.getOwner().getId());
    }
}
