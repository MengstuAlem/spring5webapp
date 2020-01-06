package com.airline.Plane.Repository;

import com.airline.Plane.entity.Plane;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PlaneRepository extends ReactiveCrudRepository<Plane,String> {
}
