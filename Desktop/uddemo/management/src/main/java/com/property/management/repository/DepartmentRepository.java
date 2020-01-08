package com.property.management.repository;

import com.property.management.entity.DepartmentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DepartmentRepository extends CrudRepository<DepartmentEntity,Long> {

    Optional <DepartmentEntity>  findByDepartmentName(String name);

}
