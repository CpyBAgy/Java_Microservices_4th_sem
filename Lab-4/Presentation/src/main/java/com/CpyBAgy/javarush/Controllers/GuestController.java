package com.CpyBAgy.javarush.Controllers;

import com.CpyBAgy.javarush.DTO.UserDTO;
import com.CpyBAgy.javarush.Models.UserModels.SignUpModel;
import com.CpyBAgy.javarush.Services.IUserService;
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

    @PostMapping("sign-up")
    public UserDTO signUp(@RequestBody SignUpModel model) throws AccessDeniedException {
        return service.createUser(model.login(), model.password(), model.invitationId(), model.roleAdmin());
    }
}