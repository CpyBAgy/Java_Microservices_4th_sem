package com.CpyBAgy.javarush.Services;

import com.CpyBAgy.javarush.DTO.CatDTO;
import com.CpyBAgy.javarush.Entities.Cat;
import com.CpyBAgy.javarush.Entities.Color;
import com.CpyBAgy.javarush.Filters.Filter;
import com.CpyBAgy.javarush.Filters.Specification.CatSpecification;
import com.CpyBAgy.javarush.Mappers.CatMapper;
import com.CpyBAgy.javarush.Repositories.CatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CatService implements ICatService {
    private final CatRepository repository;

    @Override
    public CatDTO createCat(String name, Date birthday, String breed, Color color) {
        CatMapper mapper = new CatMapper();
        Cat cat = new Cat();
        cat.setName(name);
        cat.setBreed(breed);
        cat.setColor(color);
        cat.setBirthday(birthday);
        cat.setFriends(new ArrayList<>());
        repository.save(cat);
        return mapper.map(cat);
    }

    @Override
    public CatDTO getCatById(Integer id) {
        Cat cat = repository.findById(id).orElseThrow(NoSuchElementException::new);
        return new CatDTO(cat.getId(), cat.getName(), cat.getBreed(), cat.getColor(), cat.getBirthday(), cat.getOwner().getId(), new ArrayList<>());
    }

    @Override
    public List<CatDTO> findCatsWithFilter(List<Filter> filters) {
        List<Cat> cats = repository.findAll(new CatSpecification(filters));
        return cats.stream()
                .map(cat -> new CatDTO(cat.getId(), cat.getName(), cat.getBreed(), cat.getColor(), cat.getBirthday(), cat.getOwner().getId(), new ArrayList<>()))
                .collect(Collectors.toList());
    }

    @Override
    public void updateCat(Integer id, String newName, String newBreed, Color newColor) {
        Cat cat = repository.findById(id).orElseThrow(NoSuchElementException::new);
        if (newName != null) {
            cat.setName(newName);
        }
        if (newColor != null) {
            cat.setColor(newColor);
        }
        if (newBreed != null) {
            cat.setBreed(newBreed);
        }
        repository.save(cat);
    }

    @Override
    public void updateName(Integer id, String newName) {
        Cat cat = repository.findById(id).orElseThrow(NoSuchElementException::new);
        cat.setName(newName);
        repository.save(cat);
    }

    @Override
    public void updateBreed(Integer id, String newBreed) {
        Cat cat = repository.findById(id).orElseThrow(NoSuchElementException::new);
        cat.setBreed(newBreed);
        repository.save(cat);
    }

    @Override
    public void updateColor(Integer id, Color newColor) {
        Cat cat = repository.findById(id).orElseThrow(NoSuchElementException::new);
        cat.setColor(newColor);
        repository.save(cat);
    }

    @Override
    public void addFriend(Integer catId, Integer friendCatId) {
        if (catId.equals(friendCatId)) {
            throw new IllegalArgumentException();
        }
        Cat cat1 = repository.findById(catId).orElseThrow(NoSuchElementException::new);
        Cat cat2 = repository.findById(friendCatId).orElseThrow(NoSuchElementException::new);
        if (cat1.getFriends().stream().anyMatch(cat -> cat.getId().equals(friendCatId))) {
            throw new IllegalArgumentException();
        }
        cat1.addFriend(cat2);
        repository.save(cat1);
    }

    @Override
    public void removeCat(Integer id) {
        repository.deleteById(id);
    }
}
