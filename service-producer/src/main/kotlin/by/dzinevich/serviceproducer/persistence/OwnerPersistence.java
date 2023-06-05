package by.dzinevich.serviceproducer.persistence;

import by.dzinevich.serviceproducer.model.PetOwnerDto;
import by.dzinevich.serviceproducer.model.PetOwnerPagedList;
import java.util.UUID;
import org.springframework.data.domain.Pageable;

public interface OwnerPersistence {
  PetOwnerPagedList listOwnersWithPets(Pageable pageable);
  PetOwnerDto listSingleOwnerPets(UUID ownerId);
  PetOwnerDto addNewOwner(PetOwnerDto petOwnerDto);
}
