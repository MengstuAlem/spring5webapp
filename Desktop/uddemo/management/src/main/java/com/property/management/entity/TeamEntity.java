package com.property.management.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "TEAM")
public class TeamEntity {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Column(name = "TEAM_NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID")
    private DepartmentEntity department;
    public TeamEntity(String name, DepartmentEntity department){
        this(name);
        this.department=department;
    }
    public TeamEntity(){}

    public TeamEntity(String name){
        this.name=name;
    }

    public TeamEntity(Long id ,String name) {
      this(name);
      this.id= id;
    }
    public TeamEntity(Long id ,String name,DepartmentEntity departmentEntity) {
      this(id,name);
      this.department=departmentEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }

}
