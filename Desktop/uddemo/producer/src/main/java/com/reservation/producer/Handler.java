package com.reservation.producer;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class Handler {


    Mono<ServerResponse> getAllReservation(ReservationRespository reservationRespository) {
        return ServerResponse.ok ().body (reservationRespository.findAll (), Reservation.class);
    }
}