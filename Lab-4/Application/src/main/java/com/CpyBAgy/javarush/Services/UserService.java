package com.CpyBAgy.javarush.Services;

import com.CpyBAgy.javarush.DTO.UserDTO;
import com.CpyBAgy.javarush.Entities.Owner;
import com.CpyBAgy.javarush.Entities.User;
import com.CpyBAgy.javarush.Entities.DefaultAdmin;
import com.CpyBAgy.javarush.Entities.Role;
import com.CpyBAgy.javarush.Mappers.UserMapper;
import com.CpyBAgy.javarush.Repositories.UserRepository;
import com.CpyBAgy.javarush.Repositories.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

import java.nio.file.AccessDeniedException;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository repository;
    private final OwnerRepository ownerRepository;
    private final PasswordEncoder passwordEncoder;
    private final DefaultAdmin defaultAdmin;

    @Override
    public UserDTO createUser(String login, String password, Integer ownerId, Boolean roleAdmin) throws AccessDeniedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        UserDetails currentOwnerDetails = repository.findByLogin(currentUsername)
                .orElseThrow(() -> new UsernameNotFoundException("Current user not found"));
        if (roleAdmin && !currentOwnerDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            throw new AccessDeniedException("Only administrators can create other administrators.");
        }

        UserMapper mapper = new UserMapper();
        var userWithSameLogin = repository.findByLogin(login);
        if (userWithSameLogin.isPresent()) {
            throw new IllegalArgumentException("User with the same login already exists");
        }

        Owner owner;
        if (ownerId == 0) {
            owner = new Owner();
            owner.setName(login);
            owner.setBirthday(new Date());
            owner.setCats(List.of());
            ownerRepository.save(owner);
        } else {
            owner = ownerRepository.findById(ownerId).orElseThrow(NoSuchElementException::new);
        }

        Role role = roleAdmin ? Role.ADMIN : Role.USER;

        User user = new User();
        user.setLogin(login);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        user.setOwner(owner);

        repository.save(user);
        return mapper.map(user);
    }

    @Override
    public UserDTO getUserById(Integer id) {
        UserMapper mapper = new UserMapper();
        var user = repository.findById(id).orElseThrow(NoSuchElementException::new);
        return mapper.map(user);
    }

    @Override
    public UserDTO updateUser(Integer id, String newLogin, String newPassword, Integer newOwnerId) {
        UserMapper mapper = new UserMapper();
        var user = repository.findById(id).orElseThrow(NoSuchElementException::new);

        if (newLogin != null)
            user.setLogin(newLogin);

        if (newPassword != null)
            user.setPassword(passwordEncoder.encode(newPassword));

        if (newOwnerId != null) {
            var owner = ownerRepository.findById(newOwnerId).orElseThrow(NoSuchElementException::new);
            user.setOwner(owner);
        }

        repository.save(user);
        return mapper.map(user);
    }

    @Override
    public void removeUser(Integer id) {
        var user = repository.findById(id).orElseThrow(NoSuchElementException::new);
        repository.delete(user);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return repository.findByLogin(login).orElseThrow(NoSuchElementException::new);
    }

    @PostConstruct
    public void addInitialAdmin() {
        if (repository.findByLogin(defaultAdmin.initialLogin()).isEmpty()) {
            User admin = new User();
            admin.setLogin(defaultAdmin.initialLogin());
            admin.setPassword(passwordEncoder.encode(defaultAdmin.initialPassword()));
            admin.setRole(Role.ADMIN);

            repository.save(admin);
        }
    }
}
