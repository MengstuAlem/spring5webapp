package com.reservation.producer.VehicleEntity;

import com.reservation.producer.VehicleEntity.VehicleEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@DataMongoTest
class VehicleDocumentRepositoryTest {


   @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;
    @Test

    public void documentTest(){
        VehicleEntity hunda=new VehicleEntity ("123","hunda","red",123);
        VehicleEntity  toyota=new VehicleEntity ("1234","toyota","red",123);
     Flux<VehicleEntity> saved= reactiveMongoTemplate.dropCollection (VehicleEntity.class).thenMany (
             Flux.just (hunda,toyota)
             .flatMap (car->reactiveMongoTemplate.save (car))
     ).thenMany (reactiveMongoTemplate.findAll (VehicleEntity.class));
      StepVerifier.create (saved)
              .expectNextMatches (vehicleEntity -> vehicleEntity.getId ().equalsIgnoreCase ("123"))
              .expectNext (toyota)
              .expectComplete ();
    }

}