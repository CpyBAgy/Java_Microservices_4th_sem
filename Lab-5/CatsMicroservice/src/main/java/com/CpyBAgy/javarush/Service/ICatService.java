package com.CpyBAgy.javarush.Service;

import com.CpyBAgy.javarush.CoreModels.CatModels.*;
import com.CpyBAgy.javarush.CoreModels.OtherModels.Filter;

import java.util.List;

public interface ICatService {
    CatDTO createCat(CatCreateModel model);

    CatDTO getCatById(Integer id);

    List<CatDTO> findCatsWithFilter(List<Filter> filters);

    void updateCat(CatUpdateModel model);

    void updateName(CatUpdateModel model);

    void updateBreed(CatUpdateModel model);

    void updateColor(CatUpdateModel model);

    void updateOwner(CatUpdateOwnerModel model);

    void addFriend(CatAddFriendModel model);

    void removeCat(Integer id);
}
