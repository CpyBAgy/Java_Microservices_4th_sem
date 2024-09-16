package com.CpyBAgy.javarush.Controllers;

import com.CpyBAgy.javarush.DTO.OwnerDTO;
import com.CpyBAgy.javarush.Filters.Filter;
import com.CpyBAgy.javarush.Filters.FilteringType;
import com.CpyBAgy.javarush.Models.OwnerModels.AddCatModel;
import com.CpyBAgy.javarush.Models.OwnerModels.CreateOwnerModel;
import com.CpyBAgy.javarush.Models.OwnerModels.UpdateOwnerModel;
import com.CpyBAgy.javarush.Services.OwnerService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/owner")
@SecurityRequirement(name = "basicAuth")
@RequiredArgsConstructor
public class OwnerController {
    private final OwnerService service;

    @PostMapping("/create")
    public OwnerDTO createOwner(@RequestBody CreateOwnerModel model) {
        return service.createOwner(model.name(), model.birthday());
    }

    @GetMapping("/get")
    public OwnerDTO getOwner(@RequestParam(value = "id") Integer id) {
        return service.getOwnerById(id);
    }

    @GetMapping("/find")
    public List<OwnerDTO> getOwnersWithFilter(@RequestParam(value = "name", required = false) String name) {
        return service.findOwnersWithFilter(manageFilters(name, FilteringType.EQUALS));
    }

    @GetMapping("/like")
    public List<OwnerDTO> getSimilarOwnersWithFilter(@RequestParam(value = "name", required = false) String name) {
        return service.findOwnersWithFilter(manageFilters(name, FilteringType.LIKE));
    }
    @PutMapping("/{id}/update")
    public OwnerDTO updateOwner(@PathVariable("id") Integer id, @RequestBody UpdateOwnerModel model) {
        service.updateOwner(id, model.newName());
        return service.getOwnerById(id);
    }

    @PutMapping("/add-cat")
    public OwnerDTO addCat(@RequestBody AddCatModel model) {
        service.addCat(model.id(), model.catId());
        return service.getOwnerById(model.id());
    }


    @DeleteMapping("/{id}/remove")
    public void removeOwner(@PathVariable("id") Integer id) {
        service.removeOwner(id);
    }

    private static List<Filter> manageFilters(String name, FilteringType filteringType) {
        List<Filter> filters = new ArrayList<>();

        if (name != null) {
            filters.add(new Filter("name", name, filteringType));
        }

        return filters;
    }
}

