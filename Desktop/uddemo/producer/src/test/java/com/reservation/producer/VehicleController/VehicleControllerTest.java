package com.reservation.producer.VehicleController;


import com.reservation.producer.VehicleEntity.VehicleEntity;
import com.reservation.producer.VehicleService.VehicleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@WebFluxTest
public class VehicleControllerTest {
    @MockBean
    private VehicleService vehicleService;
    @Autowired
    private WebTestClient webTestClient;



    @Test

    public void getAllVehiclesResponsOk(){
        Mockito.when (vehicleService.findAllVehicle ())
                .thenReturn (Flux.just (new VehicleEntity ("1","hunda","red",12)));

        webTestClient.get ().uri ("/vehicles").exchange ()
                .expectStatus ().isOk ()
                .expectHeader ().contentType (MediaType.APPLICATION_JSON)
                .expectBody ()
                .jsonPath ("@.[0].id").isEqualTo("1")
                .jsonPath ("@.[0].make").isEqualTo ("hunda");
    }


    @Test

    public void getVehicleByIdTest(){
        Mockito.when (vehicleService.findVehicleById ("123"))
                .thenReturn (Mono.just (new VehicleEntity ("123","hunda","red",12)));
        vehicleService.findVehicleById ("123")
                .subscribe (System.out::println);
        webTestClient.get ().uri ("/vehicle/123").exchange ()
                .expectStatus ().isOk ()
                .expectHeader ().contentType (MediaType.APPLICATION_JSON)
                .expectBody ()
                .json ("{\"id\":\"123\",\"make\":\"hunda\",\"colour\":\"red\",\"engineSize\":12}");

    }

    @Test
    public void getAllVehicleByMaker(){
        Mockito.when (vehicleService.findAllVehicleByMake ("hunda"))
                .thenReturn (Flux.just (new VehicleEntity ("123","hunda","red",12)
                        ,new VehicleEntity ("1234","hunda","black",12)
        ));

        webTestClient.get ().uri ("/Vihicles/make/hunda").exchange ()
                .expectStatus ().isOk ()
                .expectHeader ().contentType (MediaType.APPLICATION_JSON)
                .expectBody ()
                .json ("[{\"id\":\"123\",\"make\":\"hunda\",\"colour\":\"red\",\"engineSize\":12},{\"id\":\"1234\",\"make\":\"hunda\",\"colour\":\"black\",\"engineSize\":12}]");
    }

    @Test

    public void saveVehicleThenReturnTest() {

        VehicleEntity redHunda = new VehicleEntity ("123", "hunda", "red", 12);
        Mockito.when (vehicleService.saveVehicle (redHunda))
                .thenReturn (Mono.just (new VehicleEntity ("123", "hunda", "red", 12)));

        webTestClient.post ().uri ("/vihicles/save")
                .body (Mono.just (redHunda), VehicleEntity.class)
                .exchange ()
                .expectStatus ().isOk ();
          verify (vehicleService).saveVehicle(redHunda);


    }
    @Test
    public void deletedVihicleById(){
        Mockito.when (vehicleService.deleteVehicleById ("12")).thenReturn (Mono.empty ());
        webTestClient.delete ().uri ("/vihicle/12")
                .exchange ()
                .expectStatus ().isOk ();
    }


}
