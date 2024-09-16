package com.CpyBAgy.javarush.DTO;

import java.util.Date;

public record UserDTO(Integer id, String name, Date birthday, Integer[] cats){}
