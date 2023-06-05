package by.dzinevich.serviceproducer.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExecutorConfig {

  private final int petServiceThreadPool;

  public ExecutorConfig(@Value("${petservice.threads.size}") int petServiceThreadPool) {
    this.petServiceThreadPool = petServiceThreadPool;
  }

  @Bean("fixedThreadPool")
  public ExecutorService fixedThreadPool() {
    return Executors.newFixedThreadPool(petServiceThreadPool);
  }
}
