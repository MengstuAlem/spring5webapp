package com.airline.Plane.Configration;

import com.airline.Plane.entity.Plane;
import com.airline.Plane.service.PlaneService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class RouterHandler {
    private PlaneService planeService;

    public RouterHandler(PlaneService planeService) {
        this.planeService = planeService;
    }

   public Mono<ServerResponse> allPlanes(){

        return ServerResponse.ok ().body (planeService.planes (),Plane.class);
   }

}
