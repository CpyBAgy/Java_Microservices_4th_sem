package com.CpyBAgy.javarush.Service;

import com.CpyBAgy.javarush.CoreModels.CatModels.CatDTO;
import com.CpyBAgy.javarush.DataAccessModels.Cat;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
public final class CatCoreMapper {
    public CatDTO map(Cat cat) {
        return new CatDTO(
                cat.getId(),
                cat.getName(),
                cat.getBreed(),
                cat.getColor(),
                cat.getBirthday(),
                (cat.getOwner() != null) ? cat.getOwner().getId() : 0,
                (cat.getFriends() != null) ?
                        cat.getFriends().stream().map(Cat::getId).collect(Collectors.toList()) :
                        new ArrayList<>());
    }
}
