package by.dzinevich.serviceproducer.repository;

import by.dzinevich.serviceproducer.dao.Owner;
import by.dzinevich.serviceproducer.dao.Pet;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, UUID> {
  Set<Pet> findAllByOwner(Owner owner);
}
