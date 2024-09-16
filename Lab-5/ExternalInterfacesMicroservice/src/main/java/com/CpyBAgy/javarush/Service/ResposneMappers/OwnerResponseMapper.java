package com.CpyBAgy.javarush.Service.ResposneMappers;

import com.CpyBAgy.javarush.CoreModels.OwnerModels.OwnerDTO;
import com.CpyBAgy.javarush.ResponseModels.OwnerResponseModel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public final class OwnerResponseMapper {
    public OwnerResponseModel map(OwnerDTO ownerDTO) {
        return new OwnerResponseModel(
                ownerDTO.name(),
                ownerDTO.birthday(),
                ownerDTO.cats()
        );
    }
}
