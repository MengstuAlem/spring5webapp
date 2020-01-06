package PlaneEntity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Seat {

    String identity;

    Integer row;

    @JsonProperty("right_side")
    SeatIdentity rightSide;

    @JsonProperty("left_side")
    SeatIdentity leftSide;

    Class category;
}
