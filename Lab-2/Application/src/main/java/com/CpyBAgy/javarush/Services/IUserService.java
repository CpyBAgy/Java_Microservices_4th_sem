package com.CpyBAgy.javarush.Services;

import com.CpyBAgy.javarush.DTO.UserDTO;

import java.util.Date;

public interface IUserService {
    Integer createUser(String name, Date birthday);

    UserDTO getUserById(Integer id);

    void updateName(Integer id, String newName);

    void updateBirthday(Integer id, Date newBirthday);

    void addCat(Integer id, Integer cat_Id);

    void removeCat(Integer id, Integer cat_Id);

    void removeUser(Integer id);
}
