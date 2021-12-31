package by.dzinevich.serviceproducer.mapper;

import by.dzinevich.serviceproducer.dao.Pet;
import by.dzinevich.serviceproducer.model.PetDto;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface PetMapper {
    PetDto petToDto(Pet owner);
    Pet dtoToPet(PetDto petDto);
}
