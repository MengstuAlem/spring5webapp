package com.airline.Plane.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatIdentity {
    @JsonProperty("seat_identity")
    String seatIdentity;
}
