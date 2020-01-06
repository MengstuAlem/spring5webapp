package com.reservation.producer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

@WebFluxTest
@Import (ReservationConfiguration.class)
public class ReservationRestTest {

    @MockBean
    private ReservationRespository reservationRespository;


    @Autowired
    private WebTestClient webTestClient;

    @Test

    public void getAllResrvations(){
        Mockito.when (reservationRespository.findAll ()).thenReturn (Flux.just (new Reservation ("1","a"),new Reservation ("2","b")));
        webTestClient.get ().
                uri ("http://localhost:8080/reservations")
                .exchange ()
                .expectStatus ().isOk ()
                .expectHeader ().contentType (MediaType.APPLICATION_JSON)
                .expectBody ()
                .jsonPath ("@.[0].id").isEqualTo("1")
                .jsonPath ("@.[0].name").isEqualTo ("a");

    }

}
