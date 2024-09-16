package com.CpyBAgy.javarush.DAO;

import com.CpyBAgy.javarush.Entities.Cat;
import jakarta.persistence.EntityManager;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class CatDAO implements IDAO<Cat, Integer> {
    @NonNull
    private EntityManager manager;

    @Override
    public Integer create(Cat cat) {
        manager.getTransaction().begin();
        manager.detach(cat);
        manager.persist(cat);
        manager.getTransaction().commit();
        return cat.getId();
    }

    @Override
    public Optional<Cat> findById(Integer id) {
        return Optional.ofNullable(manager.find(Cat.class, id));
    }

    @Override
    public void update(Cat cat) {
        manager.getTransaction().begin();
        manager.detach(cat);
        manager.merge(cat);
        manager.getTransaction().commit();
    }

    @Override
    public void delete(Cat cat) {
        manager.getTransaction();
        manager.remove(cat);
        manager.getTransaction().commit();
    }
}
