package com.CpyBAgy.javarush.Services;

import com.CpyBAgy.javarush.DTO.CatDTO;
import com.CpyBAgy.javarush.Entities.Color;

import java.util.Date;

public interface ICatService {
    Integer createCat(String name, Date birthday, String breed, Color color);

    CatDTO getCatById(Integer id);

    void updateName(Integer id, String newName);

    void updateBirthday(Integer id, Date newBirthday);

    void updateBreed(Integer id, String newBreed);

    void updateColor(Integer id, Color newColor);

    void addFriend(Integer cat1_id, Integer cat2_id);

    void removeFriend(Integer cat1_id, Integer cat2_id);

    void removeCat(Integer id);
}
