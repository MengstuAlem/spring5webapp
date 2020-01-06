package com.airline.Plane.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaneModel {
    String factory;

    String model;

    String name;

    @JsonProperty("reference_name")
    String referenceName;
}
