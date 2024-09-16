package com.CpyBAgy.javarush.DAO;

import java.io.Serializable;
import java.util.Optional;

public interface IDAO<Entity, Id extends Serializable> {
    Id create(Entity entity);

    Optional<Entity> findById(Id id);

    void update(Entity entity);

    void delete(Entity entity);
}
