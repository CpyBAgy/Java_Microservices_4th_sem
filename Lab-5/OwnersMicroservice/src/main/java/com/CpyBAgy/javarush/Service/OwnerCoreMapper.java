package com.CpyBAgy.javarush.Service;


import com.CpyBAgy.javarush.CoreModels.OwnerModels.OwnerDTO;
import com.CpyBAgy.javarush.DataAccessModels.Cat;
import com.CpyBAgy.javarush.DataAccessModels.Owner;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
public final class OwnerCoreMapper {
    public OwnerDTO map(Owner owner) {
        return new OwnerDTO(
                owner.getId(),
                owner.getName(),
                owner.getBirthday(),
                (owner.getCats() != null) ?
                        owner.getCats().stream().map(Cat::getId).collect(Collectors.toList()) :
                        new ArrayList<>());
    }
}
