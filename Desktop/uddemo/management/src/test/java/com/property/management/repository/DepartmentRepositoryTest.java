package com.property.management.repository;

import com.property.management.entity.DepartmentEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class DepartmentRepositoryTest {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void findByDepartmentNameReturnDepartment(){
        DepartmentEntity savedIct = testEntityManager.persistFlushFind (new DepartmentEntity ("ict"));
        Optional <DepartmentEntity> ict= departmentRepository.findByDepartmentName ("ict");
        assertThat(ict.get ().getDepartmentName ()).isEqualTo (savedIct.getDepartmentName ());
    }

}