package com.airline.Plane.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Plane {

    @Id

    String id;
    String owner;

    PlaneModel model;

    Set<Seat> seats;

    String notes;

    @Builder
    public static Plane newPlane(String owner,PlaneModel planeModel,Set<Seat> seats,String notes){
        Plane plane = new Plane ();
        plane.owner=owner;
        plane.model=planeModel;
        plane.seats=seats;
        plane.notes=notes;

        return plane;
    }



    public Plane fromPlaneRequest(@NonNull PlaneRequest planeRequest){
        this.owner = planeRequest.getOwner();
        this.model = planeRequest.getModel();
        this.seats = planeRequest.getSeats();
        this.notes = planeRequest.getNotes();
        return this;
    }



}
