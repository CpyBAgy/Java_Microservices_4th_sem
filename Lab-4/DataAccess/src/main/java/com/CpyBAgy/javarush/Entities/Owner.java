package com.CpyBAgy.javarush.Entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "owners")
@Getter
@Setter
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Date birthday;

    @OneToMany(mappedBy = "owner")
    private List<Cat> cats;

    public void addCat(Cat cat) {
        cats.add(cat);
    }
}
