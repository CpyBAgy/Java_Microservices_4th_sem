package com.CpyBAgy.javarush.Services;

import com.CpyBAgy.javarush.DTO.UserDTO;
import com.CpyBAgy.javarush.Filters.Filter;

import java.util.Date;
import java.util.List;

public interface IUserService {
    UserDTO createUser(String name, Date birthday);

    UserDTO getUserById(Integer id);

    List<UserDTO> findUsersWithFilter(List<Filter> filters);

    void updateUser(Integer id, String newName);

    void addCat(Integer id, Integer catId);

    void removeUser(Integer id);
}
