package com.CpyBAgy.javarush.CoreModels.UserModels;

import com.CpyBAgy.javarush.DataAccessModels.User;

public record UserDTO (Integer id, String login, String password, Integer owner) {
    public User toEntity() {
        User user = new User();
        user.setId(id);
        user.setLogin(login);
        user.setPassword(password);
        user.setOwner(null);
        return user;
    }
}
