package com.CpyBAgy.javarush.Services;

import com.CpyBAgy.javarush.DTO.OwnerDTO;
import com.CpyBAgy.javarush.Filters.Filter;

import java.util.Date;
import java.util.List;

public interface IOwnerService {
    OwnerDTO createOwner(String name, Date birthday);

    OwnerDTO getOwnerById(Integer id);

    List<OwnerDTO> findOwnersWithFilter(List<Filter> filters);

    void updateOwner(Integer id, String newName);

    void addCat(Integer id, Integer catId);

    void removeOwner(Integer id);
}
