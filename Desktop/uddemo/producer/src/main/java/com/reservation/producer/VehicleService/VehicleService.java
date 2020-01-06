package com.reservation.producer.VehicleService;

import com.reservation.producer.VehicleEntity.VehicleEntity;
import com.reservation.producer.VehicleRepository.VehicleRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class VehicleService {

    private VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {

        this.vehicleRepository = vehicleRepository;
    }

    public Flux <VehicleEntity> findAllVehicle() {
        return vehicleRepository.findAll ();
    }

    public Mono <VehicleEntity> findVehicleById(String id){
        return vehicleRepository.findById (id);
    }

    public Flux<VehicleEntity> findAllVehicleByMake(String make) {
        return vehicleRepository.findByMake (make);
    }

    public Mono<Void> deleteVehicleById(String id){
        return vehicleRepository.deleteById (id);
    }
    public Mono <VehicleEntity> saveVehicle(VehicleEntity vehicleEntity){
         vehicleRepository.findById (vehicleEntity.getId ()).flatMap (vehicl -> {
             if (vehicl.getId ().equalsIgnoreCase (vehicleEntity.getId ())){
                 vehicleRepository.deleteById (vehicl.getId ());
             }
             return null;
         });
        return vehicleRepository.save (vehicleEntity);
    }


}
