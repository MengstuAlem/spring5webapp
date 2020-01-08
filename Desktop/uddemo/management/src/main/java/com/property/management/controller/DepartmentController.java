package com.property.management.controller;

import com.property.management.entity.DepartmentEntity;
import com.property.management.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/department")
public class DepartmentController {
    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping({"","/all",})

    public @ResponseBody String departmentList(Model model) throws Exception {
        model.addAttribute ("departments",departmentService.getAllDepartment ());
       return "department" ;

    }

    @GetMapping(value = "/{id}")

    public @ResponseBody String detailById(Model model, @PathVariable Long id) throws Exception {
        model.addAttribute ("department",departmentService.getDepartmentById (id));

        return "departmentDetail";

    }

    @PostMapping( value = "/create")

     public @ResponseBody String create(@RequestBody DepartmentEntity departmentEntity) throws Exception {
        departmentService.saveDepartment (departmentEntity);
       return "redirect:/department/"+departmentEntity.getId ();
    }

    @GetMapping(value = "/detailByName/{name}")
    public String detailByName(@PathVariable String name, Model model) throws Exception {
         model.addAttribute ("department",departmentService.getDepartmentName (name));
         return "departmentDetailByName";
    }
    @PostMapping("departments/{id}")
    public String deleteById(@PathVariable Long id ) throws Exception {
        departmentService.deleteDepartmentById (id);
        return "redirect:/department/";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)

    public void dapartmentNotFoud(DepartmentNotFoundException ex){

    }




}
