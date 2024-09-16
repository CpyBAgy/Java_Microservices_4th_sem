package com.CpyBAgy.javarush.Controllers;

import com.CpyBAgy.javarush.DTO.UserDTO;
import com.CpyBAgy.javarush.Services.UserService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@RequiredArgsConstructor
public class UserController {
    @NonNull
    private final UserService service;

    public Integer createUser(@NonNull String name, @NonNull Date birthdate) {
        return service.createUser(name, birthdate);
    }

    public UserDTO getUser(@NonNull Integer id) {
        return service.getUserById(id);
    }

    public void updateName(@NonNull Integer id, @NonNull String newName) {
        service.updateName(id, newName);
    }

    public void addCat(@NonNull Integer ownerId, @NonNull Integer catId) {
        service.addCat(ownerId, catId);
    }

    public void removeUser(@NonNull Integer id) {
        service.removeUser(id);
    }
}

