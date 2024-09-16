package com.CpyBAgy.javarush.Service;

import com.CpyBAgy.javarush.CoreModels.UserModels.UserDTO;
import com.CpyBAgy.javarush.ResponseModels.UserResponseModel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public final class UserResponseMapper {
    public UserResponseModel map(UserDTO userDTO) {
        return new UserResponseModel(
                userDTO.login(),
                userDTO.password(),
                userDTO.owner()
        );
    }
}
