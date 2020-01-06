package com.reservation.producer;


import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReservationPojoTest {

    @Test
    public void createReservation(){
        Reservation reservation= new Reservation(null,"name");

        assertThat(reservation.getName ()).isEqualTo ("name");
        Assert.isNull (reservation.getId (),"Object is null");
        assertEquals(reservation.getName (),"name");
    }
}
