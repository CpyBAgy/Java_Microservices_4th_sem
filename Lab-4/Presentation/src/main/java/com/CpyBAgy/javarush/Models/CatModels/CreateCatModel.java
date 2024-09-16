package com.CpyBAgy.javarush.Models.CatModels;

import com.CpyBAgy.javarush.Entities.Color;

import java.util.Date;

public record CreateCatModel(String name, Color color, String breed, Date birthday) { }