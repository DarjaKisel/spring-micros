package by.dzinevich.serviceproducer.service;

import by.dzinevich.serviceproducer.dao.Owner;
import by.dzinevich.serviceproducer.dao.Pet;
import by.dzinevich.serviceproducer.mapper.OwnerMapper;
import by.dzinevich.serviceproducer.mapper.PetMapper;
import by.dzinevich.serviceproducer.model.PetDto;
import by.dzinevich.serviceproducer.model.PetOwnerDto;
import by.dzinevich.serviceproducer.model.PetOwnerPagedList;
import by.dzinevich.serviceproducer.repository.OwnerRepository;
import by.dzinevich.serviceproducer.repository.PetRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class PetOwnerServiceImpl implements PetOwnerService {

  private final OwnerRepository ownerRepository;
  private final PetRepository petRepository;
  private final OwnerMapper ownerMapper;
  private final PetMapper petMapper;

  @Override
  public PetOwnerPagedList listOwnersWithPets(Pageable pageable) {
    Page<Owner> ownersPage = ownerRepository.findAll(pageable);

//    List<PetOwnerDto> petOwnerDtos = ownersPage.stream()
//        .peek(owner -> owner.setPets(petRepository.findAllByOwner(owner).toSet()))
//        .map(ownerMapper::ownerToDto)
//        .collect(Collectors.toList());

    return new PetOwnerPagedList(ownersPage.stream().map(ownerMapper::ownerToDto).collect(Collectors.toList()),
        PageRequest.of(ownersPage.getPageable().getPageNumber(),
            ownersPage.getPageable().getPageSize()),
        ownersPage.getTotalElements());
  }

  @Override
  public PetOwnerDto listSingleOwnerPets(UUID ownerId) {
    return ownerRepository.findById(ownerId).map(ownerMapper::ownerToDto).orElseThrow();
//        .map(owner -> {
//          owner.setPets(petRepository.findAllByOwner(owner).toSet());
//          return ownerMapper.ownerToDto(owner);
//        })
//        .orElse(null);
  }

  @Override
  public PetOwnerDto addNewOwner(PetOwnerDto petOwnerDto) {
    return ownerMapper.ownerToDto(ownerRepository.save(ownerMapper.dtoToOwner(petOwnerDto)));
  }

  @Override
  public PetOwnerDto addNewPet(UUID ownerId, PetDto petDto) {
    Pet newPet = petMapper.dtoToPet(petDto);
    newPet.setOwner(ownerRepository.findById(ownerId).orElseThrow());
    petRepository.save(newPet);

    return ownerRepository.findById(ownerId).map(ownerMapper::ownerToDto).orElseThrow();
  }

  @Override
  public PetOwnerDto updatePetInfo(UUID petId, PetDto petDto) {
    return null;
  }

  @Override
  public void deletePet(UUID petId) {

  }
}
