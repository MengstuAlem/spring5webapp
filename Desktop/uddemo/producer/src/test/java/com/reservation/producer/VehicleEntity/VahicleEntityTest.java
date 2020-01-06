package com.reservation.producer.VehicleEntity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class VahicleEntityTest {

    @Test
    public void createVehicleEntity(){
        VehicleEntity vehicleEntity= new VehicleEntity("123","hunda","red",4);
        assertThat(vehicleEntity.getId ()).isEqualTo ("123");
        assertThat (vehicleEntity.getMake ()).isEqualTo ("hunda");
    }
}
