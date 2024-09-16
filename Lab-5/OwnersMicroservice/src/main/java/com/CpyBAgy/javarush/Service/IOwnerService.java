package com.CpyBAgy.javarush.Service;


import com.CpyBAgy.javarush.CoreModels.OtherModels.Filter;
import com.CpyBAgy.javarush.CoreModels.OwnerModels.OwnerAddCatModel;
import com.CpyBAgy.javarush.CoreModels.OwnerModels.OwnerCreateModel;
import com.CpyBAgy.javarush.CoreModels.OwnerModels.OwnerDTO;
import com.CpyBAgy.javarush.CoreModels.OwnerModels.OwnerUpdateModel;

import java.util.List;

public interface IOwnerService {
    OwnerDTO createOwner(OwnerCreateModel model);

    OwnerDTO getOwnerById(Integer id);

    List<OwnerDTO> findOwnersWithFilter(List<Filter> filters);

    void updateOwner(OwnerUpdateModel model);

    void addCat(OwnerAddCatModel model);

    void removeOwner(Integer id);
}
