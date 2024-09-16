package com.CpyBAgy.javarush.CoreModels.UserModels;

public record UserSignUpModel(Integer invitationId, String login, String password, Boolean roleAdmin) { }
