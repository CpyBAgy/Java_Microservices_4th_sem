package com.CpyBAgy.javarush.Services;

import com.CpyBAgy.javarush.DTO.CatDTO;
import com.CpyBAgy.javarush.Entities.Color;
import com.CpyBAgy.javarush.Filters.Filter;

import java.util.Date;
import java.util.List;

public interface ICatService {
    CatDTO createCat(String name, Date birthday, String breed, Color color);

    CatDTO getCatById(Integer id);

    List<CatDTO> findCatsWithFilter(List<Filter> filters);

    void updateCat(Integer id, String newName, String newBreed, Color newColor);

    void updateName(Integer id, String newName);

    void updateBreed(Integer id, String newBreed);

    void updateColor(Integer id, Color newColor);

    void addFriend(Integer catId, Integer friendCatId);

    void removeCat(Integer id);
}
