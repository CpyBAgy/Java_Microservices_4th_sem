package com.CpyBAgy.javarush.Controllers;

import com.CpyBAgy.javarush.DTO.UserDTO;
import com.CpyBAgy.javarush.Filters.Filter;
import com.CpyBAgy.javarush.Filters.FilteringType;
import com.CpyBAgy.javarush.Models.UserModels.AddCatModel;
import com.CpyBAgy.javarush.Models.UserModels.CreateUserModel;
import com.CpyBAgy.javarush.Models.UserModels.UpdateUserModel;
import com.CpyBAgy.javarush.Services.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping("/create")
    public UserDTO createOwner(@RequestBody CreateUserModel model) {
        return service.createUser(model.name(), model.birthday());
    }

    @GetMapping("/get")
    public UserDTO getOwner(@RequestParam(value = "id") Integer id) {
        return service.getUserById(id);
    }

    @GetMapping("/find")
    public List<UserDTO> getOwnersWithFilter(@RequestParam(value = "name", required = false) String name) {
        return service.findUsersWithFilter(manageFilters(name, FilteringType.EQUALS));
    }

    @GetMapping("/like")
    public List<UserDTO> getSimilarOwnersWithFilter(@RequestParam(value = "name", required = false) String name) {
        return service.findUsersWithFilter(manageFilters(name, FilteringType.LIKE));
    }
    @PutMapping("/{id}/update")
    public UserDTO updateOwner(@PathVariable("id") Integer id, @RequestBody UpdateUserModel model) {
        service.updateUser(id, model.newName());
        return service.getUserById(id);
    }

    @PutMapping("/add-cat")
    public UserDTO addCat(@RequestBody AddCatModel model) {
        service.addCat(model.id(), model.catId());
        return service.getUserById(model.id());
    }


    @DeleteMapping("/{id}/remove")
    public void removeOwner(@PathVariable("id") Integer id) {
        service.removeUser(id);
    }

    private static List<Filter> manageFilters(String name, FilteringType filteringType) {
        List<Filter> filters = new ArrayList<>();

        if (name != null) {
            filters.add(new Filter("name", name, filteringType));
        }

        return filters;
    }
}

