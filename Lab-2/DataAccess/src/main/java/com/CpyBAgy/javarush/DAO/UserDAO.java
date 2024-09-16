package com.CpyBAgy.javarush.DAO;

import com.CpyBAgy.javarush.Entities.User;
import jakarta.persistence.EntityManager;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserDAO implements IDAO<User, Integer> {
    @NonNull
    private EntityManager manager;

    @Override
    public Integer create(User user) {
        manager.getTransaction().begin();
        manager.detach(user);
        manager.persist(user);
        manager.getTransaction().commit();
        return user.getId();
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.ofNullable(manager.find(User.class, id));
    }

    @Override
    public void update(User user) {
        manager.getTransaction().begin();
        manager.detach(user);
        manager.merge(user);
        manager.getTransaction().commit();
    }

    @Override
    public void delete(User user) {
        manager.getTransaction();
        manager.remove(user);
        manager.getTransaction().commit();
    }
}
