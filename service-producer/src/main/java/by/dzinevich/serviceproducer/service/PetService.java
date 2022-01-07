package by.dzinevich.serviceproducer.service;

import by.dzinevich.serviceproducer.model.PetDto;
import java.util.UUID;

public interface PetService {
  PetDto addNewPet(UUID ownerId, PetDto petDto);
  PetDto updatePetInfo(UUID petId, PetDto petDto);
  void deletePet(UUID petId);
}
