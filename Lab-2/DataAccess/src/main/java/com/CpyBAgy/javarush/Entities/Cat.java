package com.CpyBAgy.javarush.Entities;

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
    private Integer id;

    private String name;

    private Date birthday;

    private String breed;

    @Enumerated(EnumType.STRING)
    private Color color;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(name = "friends", joinColumns = @JoinColumn(name = "cat1_id"), inverseJoinColumns = @JoinColumn(name = "cat2_id"))
    private List<Cat> friends;

    public void addFriend(Cat cat2) {
        friends.add(cat2);
    }

    public void removeFriend(Cat cat2) {
        friends.remove(cat2);
    }
}
