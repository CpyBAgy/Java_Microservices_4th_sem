package com.CpyBAgy.javarush.DTO;

import java.util.Date;
import java.util.List;

public record OwnerDTO(Integer id, String name, Date birthday, List<Integer> cats) { }
