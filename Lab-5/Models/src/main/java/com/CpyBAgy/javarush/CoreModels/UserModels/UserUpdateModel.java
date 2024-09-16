package com.CpyBAgy.javarush.CoreModels.UserModels;

import lombok.NonNull;

public record UserUpdateModel(@NonNull Integer id, String newLogin, Integer newOwnerId) { }
