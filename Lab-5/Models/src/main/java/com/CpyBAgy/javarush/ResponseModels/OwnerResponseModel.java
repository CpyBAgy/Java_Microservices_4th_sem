package com.CpyBAgy.javarush.ResponseModels;

import java.util.Date;
import java.util.List;

public record OwnerResponseModel(String name, Date birthday, List<Integer> cats) { }
