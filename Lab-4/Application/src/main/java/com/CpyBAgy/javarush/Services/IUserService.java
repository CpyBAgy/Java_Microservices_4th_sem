package com.CpyBAgy.javarush.Services;

import com.CpyBAgy.javarush.DTO.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.nio.file.AccessDeniedException;


public interface IUserService extends UserDetailsService {
    UserDTO createUser(String login, String password, Integer ownerId, Boolean roleAdmin) throws AccessDeniedException;

    UserDTO getUserById(Integer id);

    UserDTO updateUser(Integer id, String newLogin, String newPassword, Integer newUserId);

    void removeUser(Integer id);
}