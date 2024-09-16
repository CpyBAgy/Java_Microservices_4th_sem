package com.CpyBAgy.javarush.Services;

import com.CpyBAgy.javarush.DTO.CatDTO;
import com.CpyBAgy.javarush.DAO.IDAO;
import com.CpyBAgy.javarush.Entities.Cat;
import com.CpyBAgy.javarush.Entities.Color;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Date;


@RequiredArgsConstructor
public class CatService implements ICatService {
    @NonNull
    private IDAO<Cat, Integer> dao;

    @Override
    public Integer createCat(String name, Date birthday, String breed, Color color) {
        Cat cat = new Cat();
        cat.setName(name);
        cat.setBreed(breed);
        cat.setColor(color);
        cat.setBirthday(birthday);
        cat.setFriends(new ArrayList<>());
        return dao.create(cat);
    }

    @Override
    public CatDTO getCatById(Integer id) {
        Cat cat = dao.findById(id).orElseThrow();
        return new CatDTO(
                cat.getId(),
                cat.getName(),
                cat.getBreed(),
                cat.getColor(),
                cat.getBirthday(),
                (cat.getFriends() != null) ?
                        cat.getFriends().stream().map(Cat::getId).toArray(Integer[]::new) :
                        new Integer[0]);
    }

    @Override
    public void updateName(Integer id, String newName) {
        Cat cat = dao.findById(id).orElseThrow();
        cat.setName(newName);
        dao.update(cat);
    }

    @Override
    public void updateBirthday(Integer id, Date newBirthday) {
        Cat cat = dao.findById(id).orElseThrow();
        cat.setBirthday(newBirthday);
        dao.update(cat);
    }

    @Override
    public void updateBreed(Integer id, String newBreed) {
        Cat cat = dao.findById(id).orElseThrow();
        cat.setBreed(newBreed);
        dao.update(cat);
    }

    @Override
    public void updateColor(Integer id, Color newColor) {
        Cat cat = dao.findById(id).orElseThrow();
        cat.setColor(newColor);
        dao.update(cat);
    }

    @Override
    public void addFriend(Integer cat1_id, Integer cat2_id) {
        if (cat1_id.equals(cat2_id)) {
            throw new IllegalArgumentException();
        }

        Cat cat1 = dao.findById(cat1_id).orElseThrow();
        Cat cat2 = dao.findById(cat2_id).orElseThrow();

        if (cat1.getFriends().stream().anyMatch(cat -> cat.getId().equals(cat2_id))) {
            throw new IllegalArgumentException();
        }

        cat1.addFriend(cat2);
        dao.update(cat1);
    }

    @Override
    public void removeFriend(Integer cat1_id, Integer cat2_id) {
        if (cat1_id.equals(cat2_id)) {
            throw new IllegalArgumentException();
        }

        Cat cat1 = dao.findById(cat1_id).orElseThrow();
        Cat cat2 = dao.findById(cat2_id).orElseThrow();

        if (cat1.getFriends().stream().anyMatch(cat -> cat.getId().equals(cat2_id))) {
            throw new IllegalArgumentException();
        }

        cat1.removeFriend(cat2);
        dao.update(cat1);
    }

    @Override
    public void removeCat(Integer id) {
        dao.delete(dao.findById(id).orElseThrow());
    }
}
