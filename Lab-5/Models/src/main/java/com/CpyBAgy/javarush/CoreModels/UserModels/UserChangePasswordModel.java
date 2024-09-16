package com.CpyBAgy.javarush.CoreModels.UserModels;

import lombok.NonNull;

public record UserChangePasswordModel(@NonNull Integer id, String newPassword) { }
