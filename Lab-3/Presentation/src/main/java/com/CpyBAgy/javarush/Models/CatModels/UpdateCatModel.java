package com.CpyBAgy.javarush.Models.CatModels;

import com.CpyBAgy.javarush.Entities.Color;

public record UpdateCatModel(String newName, Color newColor, String newBreed) { }
