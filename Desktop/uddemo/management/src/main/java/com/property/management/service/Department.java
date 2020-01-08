package com.property.management.service;

import com.property.management.entity.DepartmentEntity;

import java.util.List;
import java.util.Optional;

public interface Department {
    boolean saveDepartment(DepartmentEntity departmentEntity) throws Exception;

    DepartmentEntity getDepartmentById(Long id) throws Exception;

    List<DepartmentEntity> getAllDepartment() throws Exception;

    Optional <DepartmentEntity> getDepartmentName(String name) throws Exception;

    void deleteDepartmentById(long id) throws Exception;
}
