package by.dzinevich.serviceproducer.mapper;

import by.dzinevich.serviceproducer.dao.Owner;
import by.dzinevich.serviceproducer.model.PetOwnerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface OwnerMapper {
    PetOwnerDto ownerToDto(Owner owner);
    Owner dtoToOwner(PetOwnerDto petOwnerDto);
}
