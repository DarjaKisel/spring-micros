package by.dzinevich.serviceproducer.persistence;

import by.dzinevich.serviceproducer.model.PetDto;
import java.util.UUID;

public interface PetPersistence {
  PetDto addNewPet(UUID ownerId, PetDto petDto);
  PetDto updatePetInfo(UUID petId, PetDto petDto);
  void deletePet(UUID petId);
}
