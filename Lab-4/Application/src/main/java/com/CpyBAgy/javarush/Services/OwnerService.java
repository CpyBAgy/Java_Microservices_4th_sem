package com.CpyBAgy.javarush.Services;

import com.CpyBAgy.javarush.DTO.OwnerDTO;
import com.CpyBAgy.javarush.Entities.Cat;
import com.CpyBAgy.javarush.Filters.Filter;
import com.CpyBAgy.javarush.Filters.Specification.OwnerSpecification;
import com.CpyBAgy.javarush.Mappers.OwnerMapper;
import com.CpyBAgy.javarush.Repositories.CatRepository;
import com.CpyBAgy.javarush.Repositories.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OwnerService implements IOwnerService {
    private final OwnerRepository ownerRepository;
    private final CatRepository catRepository;

    @Override
    public OwnerDTO createOwner(String name, Date birthday) {
        OwnerMapper mapper = new OwnerMapper();
        com.CpyBAgy.javarush.Entities.Owner owner = new com.CpyBAgy.javarush.Entities.Owner();
        owner.setName(name);
        owner.setBirthday(birthday);
        owner.setCats(List.of());
        ownerRepository.save(owner);
        return mapper.map(owner);
    }

    @Override
    public OwnerDTO getOwnerById(Integer id) {
        com.CpyBAgy.javarush.Entities.Owner owner = ownerRepository.findById(id).orElseThrow(NoSuchElementException::new);
        List<Integer> catIds = owner.getCats().stream().map(Cat::getId).collect(Collectors.toList());
        return new OwnerDTO(owner.getId(), owner.getName(), owner.getBirthday(), catIds);
    }

    @Override
    public List<OwnerDTO> findOwnersWithFilter(List<Filter> filters) {
        List<com.CpyBAgy.javarush.Entities.Owner> owners = ownerRepository.findAll(new OwnerSpecification(filters));
        return owners.stream()
                .map(owner -> {
                    List<Integer> catIds = owner.getCats().stream().map(Cat::getId).collect(Collectors.toList());
                    return new OwnerDTO(owner.getId(), owner.getName(), owner.getBirthday(), catIds);
                })
                .collect(Collectors.toList());
    }


    @Override
    public void updateOwner(Integer id, String newName) {
        com.CpyBAgy.javarush.Entities.Owner owner = ownerRepository.findById(id).orElseThrow(NoSuchElementException::new);

        if (newName != null) {
            owner.setName(newName);
        }

        ownerRepository.save(owner);
    }

    @Override
    public void addCat(Integer id, Integer catId) {
        com.CpyBAgy.javarush.Entities.Owner owner = ownerRepository.findById(id).orElseThrow(NoSuchElementException::new);
        Cat cat = catRepository.findById(catId).orElseThrow(NoSuchElementException::new);

        if (owner.getCats().stream().anyMatch(c -> c.getId().equals(catId))) {
            throw new IllegalArgumentException("Owner already owns this cat.");
        }

        owner.addCat(cat);
        cat.setOwner(owner);

        ownerRepository.save(owner);
        catRepository.save(cat);
    }

    @Override
    public void removeOwner(Integer id) {
        ownerRepository.deleteById(id);
    }
}
