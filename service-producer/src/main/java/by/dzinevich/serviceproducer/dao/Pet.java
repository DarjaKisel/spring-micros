package by.dzinevich.serviceproducer.dao;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "pets")
public class Pet extends BaseEntity {

  @Builder
  public Pet(UUID petId,
      long version,
      LocalDateTime createdOn,
      LocalDateTime lastModifiedOn,
      String name,
      Animal animal,
      int age,
      Set<Procedure> procedures)
  {
    super(petId, version, createdOn, lastModifiedOn);

    this.name = name;
    this.animal = animal;
    this.age = age;
    this.procedures = procedures;
  }

  private String name;
  private Animal animal;
  private int age;

  @ManyToOne
  private Owner owner;

  @OneToMany(mappedBy = "Pet", cascade = CascadeType.ALL)
  @Fetch(FetchMode.JOIN)
  private Set<Procedure> procedures;

}
