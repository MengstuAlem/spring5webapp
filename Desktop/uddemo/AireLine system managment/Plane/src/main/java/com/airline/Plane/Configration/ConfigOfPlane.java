package com.airline.Plane.Configration;


import com.airline.Plane.entity.Plane;
import com.airline.Plane.entity.PlaneRequest;
import com.airline.Plane.service.PlaneService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyExtractors.toMono;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;


@Component
public class ConfigOfPlane {

    private static final String PLANES_ID = "/planes/{id}";
    public static final String PLANES = "/planes";

    @Bean
     public RouterFunction<?> routerFunction(PlaneService planeService){

        return RouterFunctions.route (RequestPredicates.GET (PLANES), serverRequest -> ok ().body (planeService.planes (),Plane.class))
                               .andRoute (RequestPredicates.POST (PLANES),
                                       serverRequest -> serverRequest.bodyToMono (Plane.class).flatMap (plane -> planeService.createPlane (plane).then (ok ().build ())))
                .andRoute (RequestPredicates.GET (PLANES_ID), serverRequest -> {
                    String id= serverRequest.pathVariable ("id");
                    return ServerResponse.ok ().body (planeService.plane (id),Plane.class);
                })
                .andRoute (RequestPredicates.DELETE (PLANES_ID), serverRequest -> planeService.plane (serverRequest.pathVariable ("id"))
                        .flatMap (plane -> planeService.deletePlane (plane).then (ok ().build ())))
                .andRoute (RequestPredicates.PUT (PLANES_ID), serverRequest ->
                        serverRequest.bodyToMono (PlaneRequest.class).flatMap (planeRequest ->
                                planeService.update (serverRequest.pathVariable ("id"),planeRequest).then (ok ().build ())));
    }
}
