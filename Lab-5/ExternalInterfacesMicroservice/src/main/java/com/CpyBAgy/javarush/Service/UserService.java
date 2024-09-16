package com.CpyBAgy.javarush.Service;


import com.CpyBAgy.javarush.CoreModels.OtherModels.DefaultAdmin;
import com.CpyBAgy.javarush.CoreModels.OtherModels.Role;
import com.CpyBAgy.javarush.CoreModels.OwnerModels.OwnerDTO;
import com.CpyBAgy.javarush.CoreModels.UserModels.UserChangePasswordModel;
import com.CpyBAgy.javarush.CoreModels.UserModels.UserDTO;
import com.CpyBAgy.javarush.CoreModels.UserModels.UserSignUpModel;
import com.CpyBAgy.javarush.CoreModels.UserModels.UserUpdateModel;
import com.CpyBAgy.javarush.DataAccess.UserRepository;
import com.CpyBAgy.javarush.DataAccessModels.User;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.NoSuchElementException;

@Service
@EnableRabbit
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final DefaultAdmin defaultAdmin;
    private final UserCoreMapper mapper;

    private final RabbitTemplate _rabbitTemplate;

    @Override
    public UserDTO createUser(UserSignUpModel model) throws AccessDeniedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        UserDetails currentUserDetails = repository.findByLogin(currentUsername)
                .orElseThrow(() -> new UsernameNotFoundException("Current user not found"));
        if (model.roleAdmin() && !currentUserDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            throw new AccessDeniedException("Only administrators can create other administrators.");
        }

        var userWithSameLogin = repository.findByLogin(model.login());
        if (userWithSameLogin.isPresent()) {
            throw new IllegalArgumentException("User with the same login already exists");
        }

        var ownerModel = _rabbitTemplate.convertSendAndReceive("ownerFindByIdQueue", model.invitationId());
        OwnerDTO owner = (OwnerDTO) ownerModel;

        Role role = model.roleAdmin() ? Role.ADMIN : Role.USER;

        User user = new User();
        user.setLogin(model.login());
        user.setPassword(passwordEncoder.encode(model.password()));
        user.setRole(role);
        user.setOwner(owner.toEntity());

        repository.save(user);
        return mapper.map(user);
    }

    @Override
    public UserDTO getUserById(Integer id) {
        return mapper.map(repository.findById(id).orElseThrow(NoSuchElementException::new));
    }

    @Override
    public UserDTO updateUser(UserUpdateModel model) {
        var user = repository.findById(model.id()).orElseThrow(NoSuchElementException::new);

        if (model.newLogin() != null)
            user.setLogin(model.newLogin());

        if (model.newOwnerId() != null) {
            var ownerModel = _rabbitTemplate.convertSendAndReceive("ownerFindByIdQueue", model.newOwnerId());
            OwnerDTO owner = (OwnerDTO) ownerModel;
            user.setOwner(owner.toEntity());
        }

        repository.save(user);
        return mapper.map(user);
    }

    @Override
    public UserDTO updatePassword(UserChangePasswordModel model) {
        var user = repository.findById(model.id()).orElseThrow(NoSuchElementException::new);

        if (model.newPassword() != null)
            user.setPassword(passwordEncoder.encode(model.newPassword()));

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
