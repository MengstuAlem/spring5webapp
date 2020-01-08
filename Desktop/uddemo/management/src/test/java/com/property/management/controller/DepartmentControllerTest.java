package com.property.management.controller;

import com.property.management.entity.DepartmentEntity;
import com.property.management.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.JsonPath;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


class DepartmentControllerTest {
    @Mock
    private DepartmentService departmentService;
    @Mock
    private Model model;
    @InjectMocks
    private DepartmentController departmentController;
    private MockMvc mockMvc;

    @BeforeEach

    public void setUp(){
        MockitoAnnotations.initMocks (this);
        departmentController= new DepartmentController (departmentService);
        mockMvc = MockMvcBuilders.standaloneSetup (departmentController).build ();
    }

    @Test
    public void callDepartment() throws Exception {
        mockMvc.perform (get("/department")).andExpect (status().isOk ());
    }
    @Test
    public void getDetailById() throws Exception {
        given(departmentService.getDepartmentById (1l))
                .willReturn (new DepartmentEntity (1l, "ict"));
        mockMvc.perform (get("/department/detailById/1"))
                .andExpect (status().isOk ())
                .andExpect (jsonPath("id").value (1L))
                .andExpect (jsonPath ("departmentName").value ("ict"));

    }

    @Test
    public void getDetailByIdNotFopund() throws Exception {
        given(departmentService.getDepartmentById (1l))
                .willThrow ( new DepartmentNotFoundException ("not found"));
        mockMvc.perform (get("/department/detailById/1"))
                .andExpect (status().isNotFound ());


    }

    @Test
    public void saveDepartment() throws Exception {
        mockMvc.perform (post ("/department/create"))
                .andExpect(status().isOk());

              
    }

    @Test

    public void ListExistingDepartment() throws Exception {
        DepartmentEntity ict= new DepartmentEntity ("ict");
        DepartmentEntity pro= new DepartmentEntity ("promotion");
        List<DepartmentEntity> departments= new ArrayList <> ();
        departments.add (ict);
        departments.add (pro);
        when (departmentService.getAllDepartment ()).thenReturn (departments);
        ArgumentCaptor<List<DepartmentEntity>>  depcap=ArgumentCaptor.forClass (List.class);
        departmentController.departmentList (model);
        verify (departmentService,times (1)).getAllDepartment ();
        verify (model,times (1)).addAttribute (eq("departments"),depcap.capture ());

        assertEquals (2,depcap.getValue ().size ());

   }


    @Test
    public void getDetailByName() throws Exception {
        given (departmentService.getDepartmentName (anyString ())).willReturn (java.util.Optional.of (new DepartmentEntity ("ict")));
        mockMvc.perform (get("/department/detailByName/name"))
                .andExpect (status().isOk ())
        .andExpect (jsonPath ("departmentName").value ("ict"));

    }

    @Test
    public void getDetailByNameThrowError() throws Exception {
        given (departmentService.getDepartmentName (anyString ())).willThrow (new DepartmentNotFoundException("not found"));
        mockMvc.perform (get("/department/detailByName/name"))
                .andExpect (status().isNotFound ());


    }





}