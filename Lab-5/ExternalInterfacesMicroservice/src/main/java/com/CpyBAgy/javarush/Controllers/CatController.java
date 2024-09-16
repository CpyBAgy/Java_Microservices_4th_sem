package com.CpyBAgy.javarush.Controllers;


import com.CpyBAgy.javarush.CoreModels.CatModels.*;
import com.CpyBAgy.javarush.CoreModels.OtherModels.Color;
import com.CpyBAgy.javarush.CoreModels.OtherModels.FilteringType;
import com.CpyBAgy.javarush.ResponseModels.CatResponseModel;
import com.CpyBAgy.javarush.Service.Filtering.CatFilterManager;
import com.CpyBAgy.javarush.Service.ResposneMappers.CatResponseMapper;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cat")
@SecurityRequirement(name = "basicAuth")
@RequiredArgsConstructor
public class CatController {
    private final CatResponseMapper mapper;

    private final RabbitTemplate _rabbitTemplate;


    @PostMapping("/create")
    public CatResponseModel createCat(@RequestBody CatCreateModel model) {
        var cat = _rabbitTemplate.convertSendAndReceive("catsCreateQueue", model);
        return mapper.map((CatDTO) cat);
    }

    @GetMapping("/get")
    public CatResponseModel getCat(@RequestParam(value = "id") Integer id) {
        var cat = _rabbitTemplate.convertSendAndReceive("catsGetByIdQueue", id);
        return mapper.map((CatDTO) cat);
    }

    @GetMapping("/equals")
    public List<CatResponseModel> getCatsWithFilterEquals(@RequestParam(value = "name", required = false) String name,
                                                @RequestParam(value = "color", required = false) Color color,
                                                @RequestParam(value = "breed", required = false) String breed) {
        var model = _rabbitTemplate.convertSendAndReceive("catsGetWithFilterQueue", new CatFilterManager().manageFilters(name, color, breed, FilteringType.EQUALS));

        List<CatDTO> cats = (List<CatDTO>) model;

        return cats
                .stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    @GetMapping("/like")
    public List<CatResponseModel> getSimilarCatsWithFilter(@RequestParam(value = "name", required = false) String name,
                                                 @RequestParam(value = "color", required = false) Color color,
                                                 @RequestParam(value = "breed", required = false) String breed) {
        var model = _rabbitTemplate.convertSendAndReceive("catsGetWithFilterQueue", new CatFilterManager().manageFilters(name, color, breed, FilteringType.LIKE));

        List<CatDTO> cats = (List<CatDTO>) model;

        return cats
                .stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}/updateName")
    public CatResponseModel updateCatName(@RequestBody CatUpdateModel model) {
        _rabbitTemplate.convertSendAndReceive("catsUpdateNameQueue", model);
        var cat = _rabbitTemplate.convertSendAndReceive("catsGetByIdQueue", model.id());
        return mapper.map((CatDTO) cat);
    }

    @PutMapping("/{id}/updateBreed")
    public CatResponseModel updateBreed(@RequestBody CatUpdateModel model) {
        _rabbitTemplate.convertSendAndReceive("catsUpdateBreedQueue", model);
        var cat = _rabbitTemplate.convertSendAndReceive("catsGetByIdQueue", model.id());
        return mapper.map((CatDTO) cat);
    }

    @PutMapping("/{id}/updateColor")
    public CatResponseModel updateColor(@RequestBody CatUpdateModel model) {
        _rabbitTemplate.convertSendAndReceive("catsUpdateColorQueue", model);
        var cat = _rabbitTemplate.convertSendAndReceive("catsGetByIdQueue", model.id());
        return mapper.map((CatDTO) cat);
    }

    @PutMapping("/{id}/updateOwner")
    public CatResponseModel updateOwner(@RequestBody CatUpdateOwnerModel model) {
        _rabbitTemplate.convertSendAndReceive("catsUpdateOwnerQueue", model);
        var cat = _rabbitTemplate.convertSendAndReceive("catsGetByIdQueue", model.id());
        return mapper.map((CatDTO) cat);
    }

    @PutMapping("/{id}/update")
    public CatResponseModel updateCat(@RequestBody CatUpdateModel model) {
        _rabbitTemplate.convertSendAndReceive("catsUpdateQueue", model);
        var cat = _rabbitTemplate.convertSendAndReceive("catsGetByIdQueue", model.id());
        return mapper.map((CatDTO) cat);
    }

    @PutMapping("/add-friend")
    public CatResponseModel addFriend(@RequestBody CatAddFriendModel model) {
        _rabbitTemplate.convertSendAndReceive("catsAddFriendQueue", model);
        var cat = _rabbitTemplate.convertSendAndReceive("catsGetByIdQueue", model.id());
        return mapper.map((CatDTO) cat);
    }


    @DeleteMapping("/{id}/remove")
    public void removeCat(@PathVariable("id") Integer id) {
        _rabbitTemplate.convertSendAndReceive("catsRemoveQueue", id);
    }
}
