package com.reservation.producer;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Stream;

@DataMongoTest
public class ReservationRespositoryTest {

    @Autowired
    private ReservationRespository reservationRespository;

    @Test

    public void findByName(){
        Flux<String> resrvationName=Flux.just ("a","b","c","d","c");
       Flux<Reservation> savedResrvation= reservationRespository.deleteAll ().
                thenMany (resrvationName.flatMap (name -> reservationRespository.save (new Reservation (null,name))))
                .thenMany (reservationRespository.findByName ("c"));

        StepVerifier.create (savedResrvation)
                .expectNextCount (2)
                .expectComplete ();
        Flux.interval (Duration.ofSeconds (1))
                .zipWith (resrvationName)
                .map (Tuple2::getT2);


    }
}
