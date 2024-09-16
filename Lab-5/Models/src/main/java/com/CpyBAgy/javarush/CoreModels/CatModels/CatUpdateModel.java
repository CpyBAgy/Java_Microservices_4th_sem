package com.CpyBAgy.javarush.CoreModels.CatModels;


import com.CpyBAgy.javarush.CoreModels.OtherModels.Color;
import lombok.NonNull;

public record CatUpdateModel(@NonNull Integer id, String newName, Color newColor, String newBreed) { }
