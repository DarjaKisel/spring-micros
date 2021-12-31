package by.dzinevich.serviceproducer.api;

import by.dzinevich.serviceproducer.model.PetOwnerDto;
import by.dzinevich.serviceproducer.model.PetDto;
import by.dzinevich.serviceproducer.model.PetOwnerPagedList;
import by.dzinevich.serviceproducer.service.PetOwnerService;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@RestController
public class PetOwnersController {

  private final PetOwnerService petOwnerService;

  @GetMapping("/")
  public ResponseEntity<PetOwnerPagedList> listOwnersWithPets(
      @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
      @RequestParam(value = "pageSize", required = false) Integer pageSize) {

    pageNumber = Optional.ofNullable(pageNumber).filter(nr -> nr > 0).orElse(0);
    pageSize = Optional.ofNullable(pageSize).filter(size -> size > 1).orElse(25);

    var owners = petOwnerService.listOwnersWithPets(PageRequest.of(pageNumber, pageSize));

    return new ResponseEntity<>(owners, HttpStatus.OK);
  }

  @GetMapping("/{ownerId}")
  public ResponseEntity<PetOwnerDto> listSingleOwnerPets(@PathVariable("ownerId") UUID ownerId) {
    var owner = petOwnerService.listSingleOwnerPets(ownerId);

    return new ResponseEntity<>(owner, HttpStatus.OK);
  }

  @PostMapping("/")
  public ResponseEntity<PetOwnerDto> addNewOwner(@Validated @RequestBody PetOwnerDto petOwnerDto) {
    return new ResponseEntity<>(petOwnerService.addNewOwner(petOwnerDto), HttpStatus.CREATED);
  }

  @PostMapping("/{ownerId}")
  public ResponseEntity<PetOwnerDto> addNewPet(@PathVariable("ownerId") UUID ownerId, @Validated @RequestBody PetDto petDto) {
    return new ResponseEntity<>(petOwnerService.addNewPet(ownerId, petDto), HttpStatus.CREATED);
  }

  @PutMapping("/{ownerId}/pets/")
  public ResponseEntity<PetOwnerDto> updatePetInfo(
      @PathVariable("ownerId") UUID ownerId,
      @Validated @RequestBody PetDto petDto) {
    return new ResponseEntity<>(petOwnerService.updatePetInfo(ownerId, petDto), HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/{ownerId}/pets/{petId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletePet(@PathVariable("ownerId") UUID ownerId, @PathVariable("petId") UUID petId) {
    petOwnerService.deletePet(petId);
  }
}
