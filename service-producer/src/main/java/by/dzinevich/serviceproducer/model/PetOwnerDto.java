package by.dzinevich.serviceproducer.model;

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
public class PetOwnerDto extends BaseDto {

    private String email;
    @NotBlank
    private String name;

    @Positive
    @NotNull
    private int age;

    Set<PetDto> pets;
}
