package by.dzinevich.serviceproducer.dao;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "owners")
@ToString(callSuper = true)
public class Owner extends BaseEntity {

  @Builder
  public Owner(UUID ownerId,
      long version,
      LocalDateTime createdDate,
      LocalDateTime lastModifiedDate,
      String email,
      String name,
      int age,
      Set<Pet> pets)
  {
    super(ownerId, version, createdDate, lastModifiedDate);

    this.email = email;
    this.name = name;
    this.age = age;
    this.pets = pets;
  }

  private String email;
  private String name;
  private int age;

  @OneToMany(mappedBy = "owner")
  @Fetch(FetchMode.JOIN)
  private Set<Pet> pets = new HashSet<>();
}
