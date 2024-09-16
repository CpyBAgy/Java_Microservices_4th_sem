package com.CpyBAgy.javarush.ResponseModels;

import com.CpyBAgy.javarush.CoreModels.OtherModels.Color;

import java.util.Date;
import java.util.List;

public record CatResponseModel(String name, String breed, Color color, Date birthday, Integer owner, List<Integer> friends) { }
