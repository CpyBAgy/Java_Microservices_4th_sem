package com.CpyBAgy.javarush.Service;


import com.CpyBAgy.javarush.CoreModels.UserModels.UserDTO;
import com.CpyBAgy.javarush.DataAccessModels.User;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public final class UserCoreMapper {
    public UserDTO map(User user) {
        return new UserDTO(
                user.getId(),
                user.getLogin(),
                user.getPassword(),
                user.getOwner().getId());
    }
}
