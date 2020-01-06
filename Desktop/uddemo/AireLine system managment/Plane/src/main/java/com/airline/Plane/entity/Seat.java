package com.airline.Plane.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seat {

    String identity;

    Integer row;

    @JsonProperty("right_side")
    SeatIdentity rightSide;

    @JsonProperty("left_side")
    SeatIdentity leftSide;


}
