package com.reservation.producer.VehicleController;

import com.reservation.producer.VehicleEntity.VehicleEntity;
import com.reservation.producer.VehicleService.VehicleService;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.reactive.function.BodyExtractor;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import static java.net.http.HttpRequest.BodyPublishers.fromPublisher;
import static org.springframework.web.reactive.function.BodyExtractors.toMono;

@SpringBootConfiguration
public class VehicleRoute {
   @Bean
   RouterFunction<?> vehicleRout(VehicleService vehicleService){
      return RouterFunctions.route (RequestPredicates.GET ("/vehicles"), serverRequest -> ServerResponse.ok ().body (vehicleService.findAllVehicle (),VehicleEntity.class))
           .andRoute (RequestPredicates.GET ("/vehicle/{id}"), serverRequest -> {
               String VehId=serverRequest.pathVariable ("id");
               return ServerResponse.ok ().body (vehicleService.findVehicleById (VehId),VehicleEntity.class);
           })
              .andRoute (RequestPredicates.GET ("/Vihicles/make/{make}"), serverRequest -> {
                  String maker= serverRequest.pathVariable ("make");
                  return ServerResponse.ok ().body (vehicleService.findAllVehicleByMake (maker),VehicleEntity.class);
              })
              .andRoute (RequestPredicates.POST ("/vihicles/save"), serverRequest -> serverRequest.body (toMono(VehicleEntity.class))
                      .doOnNext (vehicleService::saveVehicle)
                      .then (ServerResponse.ok ().build ()))
              .andRoute (RequestPredicates.DELETE ("/vihicle/{id}"), serverRequest -> {
                  String id= serverRequest.pathVariable ("id");
                  return ServerResponse.ok ().body (vehicleService.deleteVehicleById (id),VehicleEntity.class);
              });
   }

}
