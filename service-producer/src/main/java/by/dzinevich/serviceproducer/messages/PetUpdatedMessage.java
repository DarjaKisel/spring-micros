package by.dzinevich.serviceproducer.messages;

import by.dzinevich.serviceproducer.dao.Animal;
import by.dzinevich.serviceproducer.model.PetDto;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class PetUpdatedMessage {
  private UUID id;
  private String name;
  private int age;
  private Animal animal;

  public static PetUpdatedMessage fromPetDto(PetDto petDto) {
    return PetUpdatedMessage.builder()
        .id(petDto.getId())
        .name(petDto.getName())
        .age(petDto.getAge())
        .animal(petDto.getAnimal())
        .build();
  }
}
