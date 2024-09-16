package com.CpyBAgy.javarush.Service;

import com.CpyBAgy.javarush.CoreModels.CatModels.CatDTO;
import com.CpyBAgy.javarush.CoreModels.CatModels.CatUpdateOwnerModel;
import com.CpyBAgy.javarush.CoreModels.OtherModels.Filter;
import com.CpyBAgy.javarush.CoreModels.OwnerModels.OwnerAddCatModel;
import com.CpyBAgy.javarush.CoreModels.OwnerModels.OwnerCreateModel;
import com.CpyBAgy.javarush.CoreModels.OwnerModels.OwnerDTO;
import com.CpyBAgy.javarush.CoreModels.OwnerModels.OwnerUpdateModel;
import com.CpyBAgy.javarush.DataAccess.OwnerRepository;
import com.CpyBAgy.javarush.DataAccessModels.Owner;
import com.CpyBAgy.javarush.Service.Filtering.OwnerSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@EnableRabbit
@RequiredArgsConstructor
public class OwnerService implements IOwnerService {
    private final OwnerRepository ownerRepository;
    private final OwnerCoreMapper mapper;

    private final RabbitTemplate _rabbitTemplate;

    @Override
    @RabbitListener(queues = "ownersCreateQueue")
    public OwnerDTO createOwner(OwnerCreateModel model) {
        Owner owner = new Owner();
        owner.setName(model.name());
        owner.setBirthday(model.birthday());
        owner.setCats(List.of());
        ownerRepository.save(owner);
        return mapper.map(owner);
    }

    @Override
    @RabbitListener(queues = "ownersGetByIdQueue")
    public OwnerDTO getOwnerById(Integer id) {
        return mapper.map(ownerRepository.findById(id).orElseThrow(NoSuchElementException::new));
    }

    @Override
    @RabbitListener(queues = "ownersGetWithFilterQueue")
    public List<OwnerDTO> findOwnersWithFilter(List<Filter> filters) {
        List<Owner> owners = ownerRepository.findAll(new OwnerSpecification(filters));
        return owners.stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    @Override
    @RabbitListener(queues = "ownersUpdateQueue")
    public void updateOwner(OwnerUpdateModel model) {
        Owner owner = ownerRepository.findById(model.id()).orElseThrow(NoSuchElementException::new);

        if (model.newName() != null) {
            owner.setName(model.newName());
        }

        ownerRepository.save(owner);
    }

    @Override
    public void addCat(OwnerAddCatModel model) {
        Owner owner = ownerRepository.findById(model.id()).orElseThrow(NoSuchElementException::new);
        _rabbitTemplate.convertSendAndReceive("catsUpdateOwnerQueue", new CatUpdateOwnerModel(model.catId(), owner));
        var catModel = _rabbitTemplate.convertSendAndReceive("catsGetByIdQueue", model.catId());
        CatDTO cat = (CatDTO) catModel;
        owner.addCat(cat.toEntity());
        ownerRepository.save(owner);
    }

    @Override
    @RabbitListener(queues = "ownersRemoveQueue")
    public void removeOwner(Integer id) {
        ownerRepository.deleteById(id);
    }
}
