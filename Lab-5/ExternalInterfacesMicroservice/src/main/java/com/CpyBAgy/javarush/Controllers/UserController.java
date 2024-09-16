package com.CpyBAgy.javarush.Controllers;

import com.CpyBAgy.javarush.CoreModels.CatModels.CatDTO;
import com.CpyBAgy.javarush.CoreModels.OtherModels.Color;
import com.CpyBAgy.javarush.CoreModels.OtherModels.FilteringType;
import com.CpyBAgy.javarush.CoreModels.UserModels.UserChangePasswordModel;
import com.CpyBAgy.javarush.CoreModels.UserModels.UserUpdateModel;
import com.CpyBAgy.javarush.DataAccessModels.User;
import com.CpyBAgy.javarush.ResponseModels.CatResponseModel;
import com.CpyBAgy.javarush.ResponseModels.UserResponseModel;
import com.CpyBAgy.javarush.Service.Filtering.CatFilterManager;
import com.CpyBAgy.javarush.Service.IUserService;
import com.CpyBAgy.javarush.Service.ResposneMappers.CatResponseMapper;
import com.CpyBAgy.javarush.Service.UserResponseMapper;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@SecurityRequirement(name = "basicAuth")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;
    private final UserResponseMapper mapper;
    private final CatResponseMapper catMapper;

    private final RabbitTemplate _rabbitTemplate;


    @GetMapping
    public UserResponseModel getUser(@RequestParam(value = "id") Integer id){
        return mapper.map(userService.getUserById(id));
    }

    @PutMapping("/{id}/update")
    public UserResponseModel updateUser(@RequestBody UserUpdateModel model){
        return mapper.map(userService.updateUser(model));
    }

    @DeleteMapping("/{id}/remove")
    public void removeUser(@PathVariable("id") Integer id){
        userService.removeUser(id);
    }

    @PutMapping("change-password")
    public void changePassword(@RequestBody UserChangePasswordModel model) {
        userService.updatePassword(model);
    }

    @GetMapping("get-cat")
    public CatResponseModel getCat(@AuthenticationPrincipal User user, @RequestParam(value = "cat_id") Integer catId) {

        var catModel = _rabbitTemplate.convertSendAndReceive("catsGetByIdQueue", catId);
        CatDTO cat = (CatDTO) catModel;
        if (!cat.owner().equals(user.getOwner().getId()))
            throw new IllegalArgumentException();

        return catMapper.map(cat);
    }

    @GetMapping("find-cat-equals")
    public List<CatResponseModel> getCatsWithFilter(@AuthenticationPrincipal User user,
                                          @RequestParam(value = "name", required = false) String name,
                                          @RequestParam(value = "color", required = false) Color color,
                                          @RequestParam(value = "breed", required = false) String breed) {
        var model = _rabbitTemplate.convertSendAndReceive("catsGetWithFilterQueue", new CatFilterManager().manageFilters(name, color, breed, FilteringType.EQUALS));

        List<CatDTO> cats = (List<CatDTO>) model;

        return cats
                .stream()
                .filter(cat -> cat.owner().equals(user.getOwner().getId()))
                .map(catMapper::map)
                .collect(Collectors.toList());
    }

    @GetMapping("find-cat-like")
    public List<CatResponseModel> getSimilarCatsWithFilter(@AuthenticationPrincipal User user,
                                                 @RequestParam(value = "name", required = false) String name,
                                                 @RequestParam(value = "color", required = false) Color color,
                                                 @RequestParam(value = "breed", required = false) String breed) {
        var model = _rabbitTemplate.convertSendAndReceive("catsGetWithFilterQueue", new CatFilterManager().manageFilters(name, color, breed, FilteringType.LIKE));

        List<CatDTO> cats = (List<CatDTO>) model;
        return cats
                .stream()
                .filter(cat -> cat.owner().equals(user.getOwner().getId()))
                .map(catMapper::map)
                .collect(Collectors.toList());
    }
}
