package com.reservation.producer.VehicleRepository;

import com.reservation.producer.VehicleEntity.VehicleEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface VehicleRepository extends ReactiveCrudRepository<VehicleEntity,String> {

    Flux<VehicleEntity> findByMake(String make);
}
