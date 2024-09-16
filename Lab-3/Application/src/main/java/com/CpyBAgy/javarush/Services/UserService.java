package com.CpyBAgy.javarush.Services;

import com.CpyBAgy.javarush.DTO.UserDTO;
import com.CpyBAgy.javarush.Entities.Cat;
import com.CpyBAgy.javarush.Filters.Filter;
import com.CpyBAgy.javarush.Filters.Specification.UserSpecification;
import com.CpyBAgy.javarush.Mappers.UserMapper;
import com.CpyBAgy.javarush.Repositories.CatRepository;
import com.CpyBAgy.javarush.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final CatRepository catRepository;

    @Override
    public UserDTO createUser(String name, Date birthday) {
        com.CpyBAgy.javarush.Entities.User user = new com.CpyBAgy.javarush.Entities.User();
        user.setName(name);
        user.setBirthday(birthday);
        user.setCats(List.of());
        userRepository.save(user);
        return UserMapper.map(user);
    }

    @Override
    public UserDTO getUserById(Integer id) {
        com.CpyBAgy.javarush.Entities.User user = userRepository.findById(id).orElseThrow(NoSuchElementException::new);
        List<Integer> catIds = user.getCats().stream().map(Cat::getId).collect(Collectors.toList());
        return new UserDTO(user.getId(), user.getName(), user.getBirthday(), catIds);
    }

    @Override
    public List<UserDTO> findUsersWithFilter(List<Filter> filters) {
        List<com.CpyBAgy.javarush.Entities.User> users = userRepository.findAll(new UserSpecification(filters));
        return users.stream()
                .map(user -> {
                    List<Integer> catIds = user.getCats().stream().map(Cat::getId).collect(Collectors.toList());
                    return new UserDTO(user.getId(), user.getName(), user.getBirthday(), catIds);
                })
                .collect(Collectors.toList());
    }


    @Override
    public void updateUser(Integer id, String newName) {
        com.CpyBAgy.javarush.Entities.User user = userRepository.findById(id).orElseThrow(NoSuchElementException::new);

        if (newName != null) {
            user.setName(newName);
        }

        userRepository.save(user);
    }

    @Override
    public void addCat(Integer id, Integer catId) {
        com.CpyBAgy.javarush.Entities.User user = userRepository.findById(id).orElseThrow(NoSuchElementException::new);
        Cat cat = catRepository.findById(catId).orElseThrow(NoSuchElementException::new);

        if (user.getCats().stream().anyMatch(c -> c.getId().equals(catId))) {
            throw new IllegalArgumentException("User already owns this cat.");
        }

        user.addCat(cat);
        cat.setUser(user);

        userRepository.save(user);
        catRepository.save(cat);
    }

    @Override
    public void removeUser(Integer id) {
        userRepository.deleteById(id);
    }
}
