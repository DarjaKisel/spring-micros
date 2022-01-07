package by.dzinevich.serviceproducer.api;

import by.dzinevich.serviceproducer.model.PetDto;
import by.dzinevich.serviceproducer.service.PetService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@RestController
@RequestMapping("/pets")
public class PetsController {

  private final PetService petService;

  @PutMapping("/{petId}")
  public ResponseEntity<PetDto> updatePetInfo(@PathVariable("petId") UUID petId, @Validated @RequestBody PetDto petDto) {
    return new ResponseEntity<>(petService.updatePetInfo(petId, petDto), HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/{petId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletePet(@PathVariable("petId") UUID petId) {
    petService.deletePet(petId);
  }
}
