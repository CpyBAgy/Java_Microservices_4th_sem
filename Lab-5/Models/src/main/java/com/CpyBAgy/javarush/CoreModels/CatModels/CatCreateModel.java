package com.CpyBAgy.javarush.CoreModels.CatModels;

import com.CpyBAgy.javarush.CoreModels.OtherModels.Color;

import java.util.Date;

public record CatCreateModel(String name, Color color, String breed, Date birthday) { }