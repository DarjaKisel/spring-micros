package by.dzinevich.serviceproducer.service;

import by.dzinevich.serviceproducer.messages.PetDeletedMessage;
import by.dzinevich.serviceproducer.messages.PetUpdatedMessage;
import by.dzinevich.serviceproducer.messages.PetsMessagePublisher;
import by.dzinevich.serviceproducer.model.PetDto;
import by.dzinevich.serviceproducer.persistence.PetPersistence;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PetServiceImpl implements PetService {

  private final PetPersistence petPersistence;
  private final ExecutorService petServiceThreadPool;
  private final PetsMessagePublisher petsMessagePublisher;
  private final ObjectMapper objectMapper;

  public PetServiceImpl(
      PetPersistence petPersistence,
      @Qualifier("fixedThreadPool") ExecutorService petServiceThreadPool,
      PetsMessagePublisher petsMessagePublisher,
      ObjectMapper objectMapper) {
    this.petPersistence = petPersistence;
    this.petServiceThreadPool = petServiceThreadPool;
    this.petsMessagePublisher = petsMessagePublisher;
    this.objectMapper = objectMapper;
  }

  @Override
  public PetDto addNewPet(UUID ownerId, PetDto petDto) {
    return CompletableFuture
        .supplyAsync(() -> petPersistence.addNewPet(ownerId, petDto))
        .thenApplyAsync(newPet -> {
          var message = PetUpdatedMessage.fromPetDto(newPet);
          try {
            petsMessagePublisher.sendMessage(objectMapper.writeValueAsString(message));
          } catch (JsonProcessingException e) {
            log.error("Error sending created pet message={}", message);
          }

          return newPet;
        }, petServiceThreadPool).join();
  }

  @Override
  public PetDto updatePet(UUID ownerId, PetDto petDto) {
    return CompletableFuture
        .supplyAsync(() -> petPersistence.updatePetInfo(ownerId, petDto))
        .thenApplyAsync(updatedPet -> {
          var message = PetUpdatedMessage.fromPetDto(updatedPet);
          try {
            petsMessagePublisher.sendMessage(objectMapper.writeValueAsString(message));
          } catch (JsonProcessingException e) {
            log.error("Error sending updated pet message={}", message);
          }

          return updatedPet;
        }, petServiceThreadPool).join();
  }

  @Override
  public void deletePet(UUID petId) {
    petPersistence.deletePet(petId);
    var message = PetDeletedMessage.builder().id(petId).build();
    CompletableFuture
        .runAsync(() -> {
          try {
            petsMessagePublisher.sendMessage(objectMapper.writeValueAsString(message));
          } catch (JsonProcessingException e) {
            log.error("Error sending deleted pet message={}", message);
          }
        }, petServiceThreadPool);
  }
}
