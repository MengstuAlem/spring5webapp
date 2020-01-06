package com.airline.Plane.service;

import com.airline.Plane.Repository.PlaneRepository;
import com.airline.Plane.entity.Plane;
import com.airline.Plane.entity.PlaneRequest;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@Service
public class PlaneService {
    private PlaneRepository planeRepository;

    public PlaneService(PlaneRepository planeRepository) {
        this.planeRepository = planeRepository;
    }

    public Flux<Plane> planes(){
        return planeRepository.findAll ();
    }

    public Mono<Plane> plane(@NonNull String id){
        return planeRepository.findById (id);
    }

    public Mono<Plane> createPlane(@NonNull Plane plane){

        return planeRepository.save (plane);
    }

    public Mono<Plane> update(@NonNull String id,PlaneRequest planeRequest){
        return  planeRepository.findById (id)
                .flatMap (plane -> Mono.just (plane.fromPlaneRequest (planeRequest)))
                .flatMap (this.planeRepository::save);
    }

    public Mono<Void> deletePlane(@NonNull Plane plane){
        return planeRepository.delete (plane);
    }

}
