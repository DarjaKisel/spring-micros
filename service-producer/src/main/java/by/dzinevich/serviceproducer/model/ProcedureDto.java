package by.dzinevich.serviceproducer.model;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProcedureDto extends BaseDto {

  @NotNull
  private LocalDateTime dateTime;

  @NotBlank
  private String description;

  @NotNull
  private UUID doctorId;
}
