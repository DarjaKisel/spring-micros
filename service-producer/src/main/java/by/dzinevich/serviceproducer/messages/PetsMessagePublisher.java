package by.dzinevich.serviceproducer.messages;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PetsMessagePublisher {

  private final String petOwnerTopic;
  private final KafkaTemplate<String, String> kafkaTemplate;

  public PetsMessagePublisher(@Value("${petservice.kafka.topic}") String petOwnerTopic,
      KafkaTemplate<String, String> kafkaTemplate) {
    this.petOwnerTopic = petOwnerTopic;
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendMessage(String message) {
    log.debug("Sending message={}", message);
    this.kafkaTemplate.send(petOwnerTopic, message);
  }

}
