package com.CpyBAgy.javarush.Service.ResposneMappers;

import com.CpyBAgy.javarush.CoreModels.CatModels.CatDTO;
import com.CpyBAgy.javarush.ResponseModels.CatResponseModel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public final class CatResponseMapper {
    public CatResponseModel map(CatDTO catDTO) {
        return new CatResponseModel(
                catDTO.name(),
                catDTO.breed(),
                catDTO.color(),
                catDTO.birthday(),
                catDTO.owner(),
                catDTO.friends()
        );
    }
}
