package com.reservation.producer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.reactive.function.server.*;

@Configuration
public class ReservationConfiguration {


    @Bean
    RouterFunction<?> routerFunctions(ReservationRespository  reservationRespository){
        return RouterFunctions.route (RequestPredicates.GET ("/reservations"), serverRequest -> ServerResponse.ok ().body (reservationRespository.findAll (),Reservation.class));


    }


}
