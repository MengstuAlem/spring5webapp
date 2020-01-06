package com.airline.Plane;

import com.airline.Plane.Repository.PlaneRepository;
import com.airline.Plane.entity.Plane;
import com.airline.Plane.entity.PlaneModel;
import com.airline.Plane.entity.Seat;
import com.airline.Plane.entity.SeatIdentity;
import com.airline.Plane.service.PlaneService;
import org.reactivestreams.Publisher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

@SpringBootApplication
public class PlaneApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlaneApplication.class, args);
	}
//String owner,PlaneModel planeModel,Set<Seat> seats,String notes
	@Bean
	CommandLineRunner demo(PlaneRepository planeRepository){
		return args -> {
			PlaneModel planeModel= new PlaneModel ("USA","TG","Mg","");
			Set<Seat> seats= new HashSet <> ();
			seats.add (new Seat ("ETH ",1,new SeatIdentity ("right"),new SeatIdentity ("lefty")));
			seats.add (new Seat ("ETH second ",1,new SeatIdentity ("right second"),new SeatIdentity ("lefty second")));
			Plane plane = new Plane (UUID.randomUUID ().toString (),"Ethiopian air Line",planeModel,seats,"first");

			planeRepository.deleteAll ().thenMany (planeRepository.save (plane))
					.thenMany (planeRepository.findAll ()).subscribe (plane1 -> System.out.println (plane1));

		};

	}

}
