package com.CpyBAgy.javarush.Controllers;

import com.CpyBAgy.javarush.CoreModels.OtherModels.FilteringType;
import com.CpyBAgy.javarush.CoreModels.OwnerModels.OwnerCreateModel;
import com.CpyBAgy.javarush.CoreModels.OwnerModels.OwnerDTO;
import com.CpyBAgy.javarush.CoreModels.OwnerModels.OwnerUpdateModel;
import com.CpyBAgy.javarush.ResponseModels.OwnerResponseModel;
import com.CpyBAgy.javarush.Service.Filtering.OwnerFilterManager;
import com.CpyBAgy.javarush.Service.ResposneMappers.OwnerResponseMapper;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/owner")
@SecurityRequirement(name = "basicAuth")
@RequiredArgsConstructor
public class OwnerController {
    private final OwnerResponseMapper mapper;

    private final RabbitTemplate _rabbitTemplate;

    @PostMapping("/create")
    public OwnerResponseModel createOwner(@RequestBody OwnerCreateModel model) {
        var owner = _rabbitTemplate.convertSendAndReceive("ownersCreateQueue", model);
        return mapper.map((OwnerDTO) owner);
    }

    @GetMapping("/get")
    public OwnerResponseModel getOwner(@RequestParam(value = "id") Integer id) {
        var owner = _rabbitTemplate.convertSendAndReceive("ownersGetByIdQueue", id);
        return mapper.map((OwnerDTO) owner);
    }

    @GetMapping("/find")
    public List<OwnerResponseModel> getOwnersWithFilter(@RequestParam(value = "name", required = false) String name) {
        var model = _rabbitTemplate.convertSendAndReceive("ownersGetWithFilterQueue", new OwnerFilterManager().manageFilters(name, FilteringType.EQUALS));

        List<OwnerDTO> owners = (List<OwnerDTO>) model;

        return owners
                .stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    @GetMapping("/like")
    public List<OwnerResponseModel> getSimilarOwnersWithFilter(@RequestParam(value = "name", required = false) String name) {
        var model = _rabbitTemplate.convertSendAndReceive("ownersGetWithFilterQueue", new OwnerFilterManager().manageFilters(name, FilteringType.LIKE));

        List<OwnerDTO> owners = (List<OwnerDTO>) model;

        return owners
                .stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}/update")
    public OwnerResponseModel updateOwner(@RequestBody OwnerUpdateModel model) {
        _rabbitTemplate.convertSendAndReceive("ownersUpdateQueue", model);
        var owner = _rabbitTemplate.convertSendAndReceive("ownersGetByIdQueue", model.id());
        return mapper.map((OwnerDTO) owner);
    }

    @DeleteMapping("/{id}/remove")
    public void removeOwner(@PathVariable("id") Integer id) {
        _rabbitTemplate.convertSendAndReceive("ownersRemoveQueue", id);
    }
}

