package by.dzinevich.serviceproducer.service;

import by.dzinevich.serviceproducer.dao.Owner;
import by.dzinevich.serviceproducer.mapper.OwnerMapper;
import by.dzinevich.serviceproducer.model.PetOwnerDto;
import by.dzinevich.serviceproducer.model.PetOwnerPagedList;
import by.dzinevich.serviceproducer.repository.OwnerRepository;
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
  private final OwnerMapper ownerMapper;

  @Override
  public PetOwnerPagedList listOwnersWithPets(Pageable pageable) {
    Page<Owner> allOwners = ownerRepository.findAll(pageable);

    List<PetOwnerDto> petOwnerDtos = allOwners.stream()
        .map(ownerMapper::ownerToDto)
        .collect(Collectors.toList());

    return new PetOwnerPagedList(petOwnerDtos,
        PageRequest.of(allOwners.getPageable().getPageNumber(), allOwners.getPageable().getPageSize()),
        allOwners.getTotalElements());
  }

  @Override
  public PetOwnerDto listSingleOwnerPets(UUID ownerId) {
    return ownerRepository.findById(ownerId)
        .map(ownerMapper::ownerToDto)
        .orElseThrow();
  }

  @Override
  public PetOwnerDto addNewOwner(PetOwnerDto petOwnerDto) {
    return ownerMapper.ownerToDto(ownerRepository.save(ownerMapper.dtoToOwner(petOwnerDto)));
  }
}
