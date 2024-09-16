package com.CpyBAgy.javarush.Controllers;

import com.CpyBAgy.javarush.CoreModels.UserModels.UserSignUpModel;
import com.CpyBAgy.javarush.ResponseModels.UserResponseModel;
import com.CpyBAgy.javarush.Service.IUserService;
import com.CpyBAgy.javarush.Service.UserResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/guest")
@RequiredArgsConstructor
public class GuestController {
    private final IUserService service;
    private final UserResponseMapper mapper;

    @PostMapping("sign-up")
    public UserResponseModel signUp(@RequestBody UserSignUpModel model) throws AccessDeniedException {
        return mapper.map(service.createUser(model));
    }
}