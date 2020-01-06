package com.reservation.producer.VehicleRepository;

import com.reservation.producer.VehicleEntity.VehicleEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@DataMongoTest
class VehicleRepositoryTest {

  @MockBean
    private VehicleRepository vehicleRepository;
  @BeforeEach

    public void setUp(){
      Mockito.when (vehicleRepository.findByMake ("hunda")).thenReturn (Flux.just (new VehicleEntity ("123","hunda","black",12)));
  }

  @Test
    public void findVehicleByMake(){
      Flux<VehicleEntity> saved = vehicleRepository.findByMake ("hunda");

      StepVerifier.create (saved)
              .expectNextMatches (vehicleEntity -> vehicleEntity.getMake ().equalsIgnoreCase ("hunda"))
              .expectNextMatches (vehicleEntity -> vehicleEntity.getColour ().equalsIgnoreCase ("black"))
              .expectNextCount (2)
              .expectComplete ();

  }


}