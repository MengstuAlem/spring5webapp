package PlaneEntity;

import lombok.Data;

import java.util.Set;

@Data
public class PlaneRequest {
    String owner;

    PlaneModel model;

    Set<Seat> seats;

    String notes;

}
