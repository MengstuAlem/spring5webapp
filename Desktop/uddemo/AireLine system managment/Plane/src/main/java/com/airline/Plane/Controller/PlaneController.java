package com.airline.Plane.Controller;



import com.airline.Plane.entity.Plane;
import com.airline.Plane.entity.PlaneRequest;
import com.airline.Plane.service.PlaneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import javax.validation.Valid;
import java.net.URI;


@RequestMapping("/planes")
public class PlaneController {
    private PlaneService planeService;

    public PlaneController(PlaneService planeService) {
        this.planeService = planeService;
    }

    @GetMapping
    public Flux<Plane> planes(){
        return planeService.planes ();
    }

    @GetMapping("/{id}")

    public Mono<ResponseEntity<Plane>> plane(@Valid @PathVariable String id){

        return planeService.plane (id)
                .map (ResponseEntity::ok)
                .defaultIfEmpty (ResponseEntity.notFound ().build ());
    }
   @PostMapping
    public Mono<ResponseEntity<Void>> newPlane(@RequestBody Plane planeRequest, UriComponentsBuilder builder){

        return  planeService.createPlane (planeRequest)
                .map (plane -> {
                    URI connection = builder.path ("/planes/{id}")
                            .buildAndExpand (plane.getId ()).toUri ();

                    return ResponseEntity.created (connection).build ();
                });
    }


    @DeleteMapping("/{id}")

    public Mono<ResponseEntity<Object>> delete(@PathVariable String id ){

        return planeService.plane (id).flatMap (plane -> planeService.deletePlane (plane)).
                thenReturn (ResponseEntity.noContent ().build ())
                .defaultIfEmpty (new ResponseEntity <> (HttpStatus.NOT_FOUND));
    }


    @PutMapping("/{id}")
    public Mono<ResponseEntity<Object>> updatePlane(@PathVariable("id") String id,@Valid @RequestBody PlaneRequest planeRequest) {
        return this.planeService.update(id,planeRequest).then(Mono.just(ResponseEntity.ok().build()));
    }
}
