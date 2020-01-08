package com.property.management.service;

import com.property.management.entity.DepartmentEntity;
import com.property.management.repository.DepartmentRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
@Service
public class DepartmentService implements Department {

    private  DepartmentRepository departmentRepository;
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository=departmentRepository;
    }




    @Override
    public boolean saveDepartment(DepartmentEntity departmentEntity) throws Exception {
        if (departmentRepository.findById (departmentEntity.getId ()).isPresent ()){
            throw new Exception ("department exist");
        }
        departmentRepository.save (departmentEntity);
        return true;
    }

    @Override
    public DepartmentEntity getDepartmentById(Long id) throws Exception {

        if (departmentRepository.findById (id).get ().equals (null)){
            throw  new Exception ("no recorde");
        }

        return departmentRepository.findById (id).get ();
    }

    @Override
    public List<DepartmentEntity> getAllDepartment() throws Exception {
        List <DepartmentEntity> departments = (List <DepartmentEntity>) departmentRepository.findAll ();
        if (departments.size ()==0) {
            throw new Exception("no recorde");
        }

            return departments;

    }

    @Override
    public Optional <DepartmentEntity> getDepartmentName(String name) throws Exception {
        Optional <DepartmentEntity> saved=departmentRepository.findByDepartmentName (name);
        if (saved.get ().equals (null)){
            throw new Exception ("no recorde");
        }
        return departmentRepository.findByDepartmentName (name);
    }

    @Override
    public void deleteDepartmentById(long id) throws Exception {
               departmentRepository.deleteById (id);
    }




}
