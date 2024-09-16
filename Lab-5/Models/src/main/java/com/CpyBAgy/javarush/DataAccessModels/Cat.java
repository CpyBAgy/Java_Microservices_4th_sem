package com.CpyBAgy.javarush.DataAccessModels;

import com.CpyBAgy.javarush.CoreModels.OtherModels.Color;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Entity
@Table(name = "cats")
@Getter
@Setter
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "breed")
    private String breed;

    @Enumerated(EnumType.STRING)
    @Column(name = "color")
    private Color color;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToMany
    @JoinTable(name = "friends", joinColumns = @JoinColumn(name = "cat1_id"), inverseJoinColumns = @JoinColumn(name = "cat2_id"))
    private List<Cat> friends;

    public void addFriend(Cat cat2) {
        friends.add(cat2);
    }
}
