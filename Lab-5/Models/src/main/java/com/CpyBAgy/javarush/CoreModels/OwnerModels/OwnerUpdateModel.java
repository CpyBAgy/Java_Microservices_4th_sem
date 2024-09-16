package com.CpyBAgy.javarush.CoreModels.OwnerModels;

import lombok.NonNull;

public record OwnerUpdateModel(@NonNull Integer id, String newName) { }
