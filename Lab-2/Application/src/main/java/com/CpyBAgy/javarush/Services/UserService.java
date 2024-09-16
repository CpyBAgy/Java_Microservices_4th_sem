package com.CpyBAgy.javarush.Services;

import com.CpyBAgy.javarush.DTO.UserDTO;
import com.CpyBAgy.javarush.DAO.IDAO;
import com.CpyBAgy.javarush.Entities.Cat;
import com.CpyBAgy.javarush.Entities.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

@RequiredArgsConstructor
public class UserService implements IUserService {
    @NonNull
    private IDAO<User, Integer> dao;
    @NonNull
    private IDAO<Cat, Integer> catDAO;
    
    @Override
    public Integer createUser(String name, Date birthday) {
        User user = new User();
        user.setName(name);
        user.setBirthday(birthday);
        user.setCats(new ArrayList<>());
        return dao.create(user);
    }

    @Override
    public UserDTO getUserById(Integer id) {
        User user = dao.findById(id).orElseThrow();
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getBirthday(),
                (user.getCats() != null) ?
                        user.getCats().stream().map(Cat::getId).toArray(Integer[]::new) :
                        new Integer[0]);
    }

    @Override
    public void updateName(Integer id, String newName) {
        User user = dao.findById(id).orElseThrow();
        user.setName(newName);
        dao.update(user);
    }

    @Override
    public void updateBirthday(Integer id, Date newBirthday) {
        User user = dao.findById(id).orElseThrow();
        user.setBirthday(newBirthday);
        dao.update(user);
    }

    @Override
    public void addCat(Integer id, Integer cat_id) {
        User owner = dao.findById(id).orElseThrow();
        Cat cat = catDAO.findById(cat_id).orElseThrow();

        if (owner.getCats().stream().anyMatch(cat1 -> cat1.getId().equals(cat_id))) {
            throw new IllegalArgumentException();
        }

        owner.addCat(cat);
        cat.setUser(owner);

        dao.update(owner);
        catDAO.update(cat);
    }

    @Override
    public void removeCat(Integer id, Integer cat_id) {
        User owner = dao.findById(id).orElseThrow();
        Cat cat = catDAO.findById(cat_id).orElseThrow();

        if (owner.getCats().stream().anyMatch(cat1 -> cat1.getId().equals(cat_id))) {
            throw new IllegalArgumentException();
        }

        owner.removeCat(cat);

        dao.update(owner);
        catDAO.delete(cat);
    }

    @Override
    public void removeUser(Integer id) {
        dao.delete(dao.findById(id).orElseThrow());
    }
}
