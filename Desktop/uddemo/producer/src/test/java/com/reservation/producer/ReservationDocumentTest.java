package com.reservation.producer;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.function.Function;

@DataMongoTest
public class ReservationDocumentTest {
    @Autowired
    ReactiveMongoTemplate reactiveMongoTemplate;

@Test
    public void  cre(){
    Reservation reservation1= new Reservation (null,"a");
    Reservation reservation2= new Reservation (null,"a");
    Flux<Reservation> toSave =Flux.just (reservation1,reservation2);
    Flux<Reservation> saved =reactiveMongoTemplate.dropCollection (Reservation.class).thenMany (
            toSave.flatMap (reservation -> reactiveMongoTemplate::save)
    ).thenMany (reactiveMongoTemplate.findAll (Reservation.class));

    StepVerifier.create (saved).expectNext (reservation1).expectNext (reservation2).expectComplete ();



}
}
