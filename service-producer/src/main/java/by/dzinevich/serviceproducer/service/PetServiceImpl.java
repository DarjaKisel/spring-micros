package by.dzinevich.serviceproducer.service;

import by.dzinevich.serviceproducer.dao.Pet;
import by.dzinevich.serviceproducer.mapper.PetMapper;
import by.dzinevich.serviceproducer.model.PetDto;
import by.dzinevich.serviceproducer.repository.OwnerRepository;
import by.dzinevich.serviceproducer.repository.PetRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class PetServiceImpl implements PetService {

  private final OwnerRepository ownerRepository;
  private final PetRepository petRepository;
  private final PetMapper petMapper;

  @Override
  public PetDto addNewPet(UUID ownerId, PetDto petDto) {
    return ownerRepository.findById(ownerId)
        .map(owner -> {
          Pet pet = petMapper.dtoToPet(petDto);
          pet.setOwner(owner);

          return pet;
        })
        .map(petRepository::save)
        .map(petMapper::petToDto)
        .orElseThrow();
  }

  @Override
  public PetDto updatePetInfo(UUID petId, PetDto petDto) {
    return petRepository.findById(petId)
        .map(existingPet -> {
          var pet = petMapper.dtoToPet(petDto);
          pet.setId(existingPet.getId());
          pet.setOwner(existingPet.getOwner());

          return pet;
        })
        .map(petRepository::save)
        .map(petMapper::petToDto)
        .orElseThrow();
  }

  @Override
  public void deletePet(UUID petId) {
    petRepository.deleteById(petId);
  }
}
