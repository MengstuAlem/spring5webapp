package com.reservation.producer.VehicleEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleEntity {
    @Id
    private String id;
    private String make;
    private String colour;
    private int engineSize;



}
