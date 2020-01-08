package com.property.management.repository;

import com.property.management.entity.TeamEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TeamRepository extends CrudRepository<TeamEntity,Long> {

    Optional<TeamEntity> findByName(String name);
}
