package com.CpyBAgy.javarush.Service;

import com.CpyBAgy.javarush.CoreModels.UserModels.UserChangePasswordModel;
import com.CpyBAgy.javarush.CoreModels.UserModels.UserDTO;
import com.CpyBAgy.javarush.CoreModels.UserModels.UserSignUpModel;
import com.CpyBAgy.javarush.CoreModels.UserModels.UserUpdateModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.nio.file.AccessDeniedException;


public interface IUserService extends UserDetailsService {
    UserDTO createUser(UserSignUpModel model) throws AccessDeniedException;

    UserDTO getUserById(Integer id);

    UserDTO updateUser(UserUpdateModel model);

    UserDTO updatePassword(UserChangePasswordModel model);

    void removeUser(Integer id);
}