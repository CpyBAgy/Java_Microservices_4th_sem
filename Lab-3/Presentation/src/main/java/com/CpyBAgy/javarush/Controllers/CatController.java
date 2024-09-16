package com.CpyBAgy.javarush.Controllers;

import com.CpyBAgy.javarush.DTO.CatDTO;
import com.CpyBAgy.javarush.Entities.Color;
import com.CpyBAgy.javarush.Filters.Filter;
import com.CpyBAgy.javarush.Filters.FilteringType;
import com.CpyBAgy.javarush.Models.CatModels.AddFriendModel;
import com.CpyBAgy.javarush.Models.CatModels.CreateCatModel;
import com.CpyBAgy.javarush.Models.CatModels.UpdateCatModel;
import com.CpyBAgy.javarush.Services.ICatService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cat")
@RequiredArgsConstructor
public class CatController {
    private final ICatService service;

    @PostMapping("/create")
    public CatDTO createCat(@RequestBody CreateCatModel model) {
        return service.createCat(model.name(), model.birthday(), model.breed(), model.color());
    }

    @GetMapping("/get")
    public CatDTO getCat(@RequestParam(value = "id") Integer id) {
        return service.getCatById(id);
    }

    @GetMapping("/equals")
    public List<CatDTO> getCatsWithFilter(@RequestParam(value = "name", required = false) String name,
                                          @RequestParam(value = "color", required = false) Color color,
                                          @RequestParam(value = "breed", required = false) String breed) {
        return service.findCatsWithFilter(manageFilters(name, color, breed, FilteringType.EQUALS));
    }

    @GetMapping("/like")
    public List<CatDTO> getSimilarCatsWithFilter(@RequestParam(value = "name", required = false) String name,
                                                 @RequestParam(value = "color", required = false) Color color,
                                                 @RequestParam(value = "breed", required = false) String breed) {
        return service.findCatsWithFilter(manageFilters(name, color, breed, FilteringType.LIKE));
    }

    @PutMapping("/{id}/updateName")
    public CatDTO updateCatName(@PathVariable("id") Integer id, @RequestBody UpdateCatModel model) {
        service.updateName(id, model.newName());
        return service.getCatById(id);
    }

    @PutMapping("/{id}/updateBreed")
    public CatDTO updateBreed(@PathVariable("id") Integer id, @RequestBody UpdateCatModel model) {
        service.updateBreed(id, model.newBreed());
        return service.getCatById(id);
    }

    @PutMapping("/{id}/updateColor")
    public CatDTO updateColor(@PathVariable("id") Integer id, @RequestBody UpdateCatModel model) {
        service.updateColor(id, model.newColor());
        return service.getCatById(id);
    }

    @PutMapping("/{id}/update")
    public CatDTO updateCat(@PathVariable("id") Integer id, @RequestBody UpdateCatModel model) {
        service.updateCat(id, model.newName(), model.newBreed(), model.newColor());
        return service.getCatById(id);
    }

    @PutMapping("/add-friend")
    public CatDTO addFriend(@RequestBody AddFriendModel model) {
        service.addFriend(model.id(), model.friendCatId());
        return service.getCatById(model.id());
    }


    @DeleteMapping("/{id}/remove")
    public void removeCat(@PathVariable("id") Integer id) {
        service.removeCat(id);
    }

    private static List<Filter> manageFilters(String name, Color color, String breed, FilteringType filteringType) {
        List<Filter> filters = new ArrayList<>();

        if (name != null) {
            filters.add(new Filter("name", name, filteringType));
        }
        if (color != null) {
            filters.add(new Filter("color", color.toString(), filteringType));
        }
        if (breed != null) {
            filters.add(new Filter("breed", breed, filteringType));
        }

        return filters;
    }
}
