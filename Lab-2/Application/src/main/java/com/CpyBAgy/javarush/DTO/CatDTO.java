package com.CpyBAgy.javarush.DTO;

import com.CpyBAgy.javarush.Entities.Color;

import java.util.Date;

public record CatDTO(Integer id, String name, String breed, Color color, Date birthday, Integer[] friends) {}
