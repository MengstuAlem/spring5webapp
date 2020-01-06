package com.reservation.producer;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ReservationRespository extends ReactiveMongoRepository<Reservation,String> {

    Flux<Reservation> findByName(String name);
}
