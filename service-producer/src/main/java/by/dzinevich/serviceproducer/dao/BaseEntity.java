package by.dzinevich.serviceproducer.dao;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

@Setter
@Getter
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {

  public BaseEntity(UUID id,
      Long version,
      LocalDateTime createdOn,
      LocalDateTime lastModifiedOn) {
    this.id = id;
    this.version = version;
    this.createdOn = createdOn;
    this.lastModifiedOn = lastModifiedOn;
  }

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(
      name = "UUID",
      strategy = "org.hibernate.id.UUIDGenerator"
  )
  @Type(type = "org.hibernate.type.UUIDCharType")
  @Column(
      length = 36,
      columnDefinition = "varchar(36)",
      updatable = false,
      nullable = false
  )
  private UUID id;

  @Version
  @Column(columnDefinition = "integer DEFAULT 0", nullable = false)
  private long version = 0L;

  @CreationTimestamp
  @Column(updatable = false)
  private LocalDateTime createdOn;
  @UpdateTimestamp
  private LocalDateTime lastModifiedOn;

  public boolean isNew() {
    return this.id == null;
  }
}
