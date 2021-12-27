package by.dzinevich.serviceproducer.dao;


import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "pet_procedures")
public class Procedure extends BaseEntity {

  @Builder
  public Procedure(UUID petId,
      long version,
      LocalDateTime createdDate,
      LocalDateTime lastModifiedDate,
      LocalDateTime dateTime,
      String description)
  {
    super(petId, version, createdDate, lastModifiedDate);

    this.dateTime = dateTime;
    this.description = description;
  }

  @ManyToOne
  private Pet pet;

  private LocalDateTime dateTime;
  private String description;
}
