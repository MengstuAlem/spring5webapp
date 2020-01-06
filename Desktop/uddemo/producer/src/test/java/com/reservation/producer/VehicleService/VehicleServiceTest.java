package com.reservation.producer.VehicleService;

import com.reservation.producer.VehicleEntity.VehicleEntity;
import com.reservation.producer.VehicleRepository.VehicleRepository;
import com.reservation.producer.VehicleService.VehicleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;
@SpringBootTest
public class VehicleServiceTest {

    private VehicleService vehicleService;
    @MockBean
    private VehicleRepository vehicleRepository;
    private VehicleEntity redHunda;
    private VehicleEntity blackHunda;
    @BeforeEach
    public void setUp(){
        redHunda=new VehicleEntity ("1234","hunda","Red",12);
        blackHunda= new VehicleEntity ("123","hunda","black",12);
       MockitoAnnotations.initMocks (this);
       vehicleService= new VehicleService (vehicleRepository);
        when (vehicleRepository.findAll ()).thenReturn (Flux.just (redHunda,blackHunda));
        when (vehicleRepository.findById ("1234")).thenReturn (Mono.just (redHunda));
        when (vehicleRepository.findByMake ("hunda")).thenReturn (Flux.just (redHunda,blackHunda));
        when (vehicleRepository.save (redHunda)).thenReturn (Mono.just (redHunda));

    }


    @Test
    public void findAllTest(){


        Flux<VehicleEntity> saved=vehicleService.findAllVehicle();
        StepVerifier.create (saved)

                .expectNext (redHunda)
                .expectNext (blackHunda)

                .expectComplete ()
        .verify ();


    }

    @Test
    public void findByIdTest(){

        StepVerifier.create (vehicleService.findVehicleById( "1234"))
                .expectNextMatches (vehicleEntity -> vehicleEntity.getColour ().equalsIgnoreCase ("red"))
                .expectComplete ()
        .verify ();

    }

    @Test

    public void findByMakeTest(){
        StepVerifier.create (vehicleService.findAllVehicleByMake("hunda"))
                .expectNext (redHunda)
                .expectNext (blackHunda)
                .expectComplete ()
                .verify ();
    }

    @Test

    public void saveVehicleTest(){
        StepVerifier.create (vehicleService.saveVehicle (redHunda))
                .expectNext (redHunda)
                .expectComplete ()
                .verify ();

    }
}
