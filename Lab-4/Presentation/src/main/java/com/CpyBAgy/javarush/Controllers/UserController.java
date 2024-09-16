package com.CpyBAgy.javarush.Controllers;

import com.CpyBAgy.javarush.DTO.CatDTO;
import com.CpyBAgy.javarush.DTO.UserDTO;
import com.CpyBAgy.javarush.Entities.User;
import com.CpyBAgy.javarush.Entities.Color;
import com.CpyBAgy.javarush.Filters.CatFilterManager;
import com.CpyBAgy.javarush.Filters.Filter;
import com.CpyBAgy.javarush.Filters.FilteringType;
import com.CpyBAgy.javarush.Models.UserModels.ChangePasswordModel;
import com.CpyBAgy.javarush.Models.UserModels.UpdateUserModel;
import com.CpyBAgy.javarush.Services.ICatService;
import com.CpyBAgy.javarush.Services.IUserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@SecurityRequirement(name = "basicAuth")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;
    private final ICatService catService;
    @GetMapping
    public UserDTO getUser(@RequestParam(value = "id") Integer id){
        return userService.getUserById(id);
    }

    @PutMapping("/{id}/update")
    public UserDTO updateUser(@PathVariable("id") Integer id, @RequestBody UpdateUserModel model){
        return userService.updateUser(id, model.newLogin(), null, model.newOwnerId());
    }

    @DeleteMapping("/{id}/remove")
    public void removeUser(@PathVariable("id") Integer id){
        userService.removeUser(id);
    }

    @PutMapping("change-password")
    public void changePassword(@AuthenticationPrincipal User user, @RequestBody ChangePasswordModel model) {
        userService.updateUser(user.getId(), null, model.newPassword(), null);
    }

    @GetMapping("get-cat")
    public CatDTO getCat(@AuthenticationPrincipal User user, @RequestParam(value = "cat_id") Integer catId) {
        CatDTO cat = catService.getCatById(catId);
        if (!cat.owner().equals(user.getOwner().getId()))
            throw new IllegalArgumentException();

        return cat;
    }

    @GetMapping("find-cat-equals")
    public List<CatDTO> getCatsWithFilter(@AuthenticationPrincipal User user,
                                          @RequestParam(value = "name", required = false) String name,
                                          @RequestParam(value = "color", required = false) Color color,
                                          @RequestParam(value = "breed", required = false) String breed) {
        List<Filter> filters = CatFilterManager.manage(name, color, breed, FilteringType.EQUALS);
        List<CatDTO> cats = catService.findCatsWithFilter(filters);
        return cats.stream()
                .filter(cat -> cat.owner().equals(user.getOwner().getId()))
                .collect(Collectors.toList());
    }

    @GetMapping("find-cat-like")
    public List<CatDTO> getSimilarCatsWithFilter(@AuthenticationPrincipal User user,
                                                 @RequestParam(value = "name", required = false) String name,
                                                 @RequestParam(value = "color", required = false) Color color,
                                                 @RequestParam(value = "breed", required = false) String breed) {
        List<Filter> filters = CatFilterManager.manage(name, color, breed, FilteringType.LIKE);
        List<CatDTO> cats = catService.findCatsWithFilter(filters);
        return cats.stream()
                .filter(cat -> cat.owner().equals(user.getOwner().getId()))
                .collect(Collectors.toList());    }
}
