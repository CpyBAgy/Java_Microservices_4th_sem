package com.CpyBAgy.javarush.DTO;


import com.CpyBAgy.javarush.Entities.Color;

import java.util.Date;
import java.util.List;

public record CatDTO(Integer id, String name, String breed, Color color, Date birthday, Integer user, List<Integer> friends) { }
