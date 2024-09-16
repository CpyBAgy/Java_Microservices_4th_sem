package com.CpyBAgy.javarush.Service;


import com.CpyBAgy.javarush.CoreModels.CatModels.*;
import com.CpyBAgy.javarush.CoreModels.OtherModels.Filter;
import com.CpyBAgy.javarush.DataAccess.CatRepository;
import com.CpyBAgy.javarush.DataAccessModels.Cat;
import com.CpyBAgy.javarush.Service.Filtering.CatSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@EnableRabbit
@RequiredArgsConstructor
public class CatService implements ICatService {
    private final CatRepository repository;
    private final CatCoreMapper mapper;

    @Override
    @RabbitListener(queues = "catsCreateQueue")
    public CatDTO createCat(CatCreateModel model) {
        Cat cat = new Cat();
        cat.setName(model.name());
        cat.setBreed(model.breed());
        cat.setColor(model.color());
        cat.setBirthday(model.birthday());
        cat.setFriends(new ArrayList<>());
        repository.save(cat);
        return mapper.map(cat);
    }

    @Override
    @RabbitListener(queues = "catsGetByIdQueue")
    public CatDTO getCatById(Integer id) {
        return mapper.map(repository.findById(id).orElseThrow(NoSuchElementException::new));
    }

    @Override
    @RabbitListener(queues = "catsGetWithFilterQueue")
    public List<CatDTO> findCatsWithFilter(List<Filter> filters) {
        return repository.findAll(new CatSpecification(filters))
                .stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    @Override
    @RabbitListener(queues = "catsUpdateQueue")
    public void updateCat(CatUpdateModel model) {
        Cat cat = repository.findById(model.id()).orElseThrow(NoSuchElementException::new);
        if (model.newName() != null) {
            cat.setName(model.newName());
        }
        if (model.newColor() != null) {
            cat.setColor(model.newColor());
        }
        if (model.newBreed() != null) {
            cat.setBreed(model.newBreed());
        }
        repository.save(cat);
    }

    @Override
    @RabbitListener(queues = "catsUpdateNameQueue")
    public void updateName(CatUpdateModel model) {
        Cat cat = repository.findById(model.id()).orElseThrow(NoSuchElementException::new);
        cat.setName(model.newName());
        repository.save(cat);
    }

    @Override
    @RabbitListener(queues = "catsUpdateBreedQueue")
    public void updateBreed(CatUpdateModel model) {
        Cat cat = repository.findById(model.id()).orElseThrow(NoSuchElementException::new);
        cat.setBreed(model.newBreed());
        repository.save(cat);
    }

    @Override
    @RabbitListener(queues = "catsUpdateColorQueue")
    public void updateColor(CatUpdateModel model) {
        Cat cat = repository.findById(model.id()).orElseThrow(NoSuchElementException::new);
        cat.setColor(model.newColor());
        repository.save(cat);
    }

    @Override
    @RabbitListener(queues = "catsUpdateOwnerQueue")
    public void updateOwner(CatUpdateOwnerModel model) {
        Cat cat = repository.findById(model.id()).orElseThrow(NoSuchElementException::new);
        cat.setOwner(model.owner());
        repository.save(cat);
    }

    @Override
    @RabbitListener(queues = "catsAddFriendQueue")
    public void addFriend(CatAddFriendModel model) {
        if (model.id().equals(model.friendCatId())) {
            throw new IllegalArgumentException();
        }
        Cat cat1 = repository.findById(model.id()).orElseThrow(NoSuchElementException::new);
        Cat cat2 = repository.findById(model.friendCatId()).orElseThrow(NoSuchElementException::new);
        if (cat1.getFriends().stream().anyMatch(cat -> cat.getId().equals(model.friendCatId()))) {
            throw new IllegalArgumentException();
        }
        cat1.addFriend(cat2);
        repository.save(cat1);
    }

    @Override
    @RabbitListener(queues = "catsRemoveQueue")
    public void removeCat(Integer id) {
        repository.deleteById(id);
    }
}
