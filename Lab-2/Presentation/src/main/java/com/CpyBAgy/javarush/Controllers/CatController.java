package com.CpyBAgy.javarush.Controllers;

import com.CpyBAgy.javarush.DTO.CatDTO;
import com.CpyBAgy.javarush.Entities.Color;
import com.CpyBAgy.javarush.Services.CatService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@RequiredArgsConstructor
public class CatController {
    @NonNull
    private final CatService service;

    public Integer createCat(@NonNull String name, @NonNull Date birthday, @NonNull String breed, @NonNull Color color) {
        return service.createCat(name, birthday, breed, color);
    }

    public CatDTO getCat(@NonNull Integer id) {
        return service.getCatById(id);
    }

    public void updateName(@NonNull Integer id, @NonNull String newName) {
        service.updateName(id, newName);
    }

    public void addFriend(@NonNull Integer id, @NonNull Integer friendId) {
        service.addFriend(id, friendId);
    }

    public void removeCat(@NonNull Integer id) {
        service.removeCat(id);
    }
}
