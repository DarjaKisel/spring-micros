package by.dzinevich.serviceproducer.model;

import by.dzinevich.serviceproducer.dao.Animal;
import by.dzinevich.serviceproducer.dao.Procedure;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PetDto extends BaseDto {

  @NotBlank
  private String name;

  @NotNull
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private Animal animal;

  @Positive
  private int age;

  private Set<Procedure> procedures;
}
