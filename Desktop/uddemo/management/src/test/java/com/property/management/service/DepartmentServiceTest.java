package com.property.management.service;

import com.property.management.entity.DepartmentEntity;
import com.property.management.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class DepartmentServiceTest {

    @InjectMocks
    private DepartmentService departmentService;
    @Mock
    private DepartmentRepository departmentRepository;
    private DepartmentEntity ict;


    @BeforeEach
    public void  setUp(){
        MockitoAnnotations.initMocks (this);
        departmentService = new DepartmentService (departmentRepository);
        ict = new DepartmentEntity (1l,"ict");
    }

    @Test

    public void saveDepartment() throws Exception {

        when(departmentRepository.save (ict)).thenReturn (ict);
        assertTrue (departmentService.saveDepartment(ict));
        verify (departmentRepository,times (1)).save (ict);
    }

    @Test
    public void saveExistDepartmentThrowsException() throws Exception {
        DepartmentEntity ict1 = new DepartmentEntity (1l,"ict");
        when(departmentRepository.save (ict)).thenReturn (ict);
        departmentService.saveDepartment (ict);
        departmentService.saveDepartment (ict1);

        assertThrows (Exception.class,() -> {
            Integer.parseInt("One");
        });
        verify (departmentRepository,times (1)).save (ict);

    }

    @Test
    public void getExistingDepartmentById() throws Exception {
        Long id= ict.getId ();
        when (departmentRepository.findById (id)).thenReturn (java.util.Optional.ofNullable (ict));
        assertEquals ("ict",departmentService.getDepartmentById (id).getDepartmentName ());
        verify (departmentRepository,times (2)).findById (id);

    }

    @Test
    public void getNonExistingDepartmentById() throws Exception {
        Long id= 2l;
        when (departmentRepository.findById (id)).thenReturn (java.util.Optional.ofNullable(null));

        assertThrows (Exception.class,() -> {
            departmentService.getDepartmentById (id);
        });

        verify (departmentRepository,times (1)).findById (id);

    }
    @Test
    public void getAllDepartment() throws Exception {
        List<DepartmentEntity> departmets= new ArrayList <> ();
        departmets.add (ict);
        departmets.add (new DepartmentEntity (2,"promotion"));
        when (departmentRepository.findAll ()).thenReturn (departmets);
        assertEquals (2,departmentService.getAllDepartment().size());
        verify (departmentRepository,times (1)).findAll ();

    }

    @Test
    public void getAllDepartmentNoRecord() throws Exception {
        List<DepartmentEntity> departments= new ArrayList <> ();
        when (departmentRepository.findAll ()).thenReturn (departments);

        assertThrows (Exception.class,() -> {
            departmentService.getAllDepartment ();
        });

        verify (departmentRepository,times (1)).findAll ();

    }

    @Test
    public void getExistingDepartmentByName() throws Exception {
        String name = ict.getDepartmentName ();
        when (departmentRepository.findByDepartmentName(name)).thenReturn (java.util.Optional.ofNullable (ict));
        assertEquals ("ict",departmentService.getDepartmentName (name).get ().getDepartmentName ());
        verify (departmentRepository,times (2)).findByDepartmentName (name);
    }

    @Test
    public void getNoRecordeDepartmentByName() throws Exception {
        String name = "promotion";
         when (departmentRepository.findByDepartmentName(name)).thenReturn (null);
       assertThrows (Exception.class,()->{
           departmentService.getDepartmentName (name);
       });

        verify (departmentRepository,times (1)).findByDepartmentName (name);
    }


    @Test
    public void deleteDepartmentById() throws Exception {
        departmentService.deleteDepartmentById (1l);
        verify (departmentRepository,times (1)).deleteById (1l);

    }
}