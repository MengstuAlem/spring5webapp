package com.property.management.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "DEPARTMENT")
public class DepartmentEntity {
    @Id
    @GeneratedValue
    @Column(name = "DEPARTMENT_ID")
    private Long id;
    @Column(name = "DEPARTMENT_NAME")
    private String departmentName;
    @OneToMany(mappedBy = "department",cascade = CascadeType.ALL)
    private List<TeamEntity> teams=new ArrayList <> ();
    public DepartmentEntity(){}

    public DepartmentEntity(String departmentName, List <TeamEntity> teamPromotion){
        this(departmentName);
        this.teams=teamPromotion;
    }

    public DepartmentEntity(String departmentName) {
        this.departmentName = departmentName;
    }

    public DepartmentEntity(long id, String departmentName) {
        this(departmentName);
        this.id=id;
    }

    public DepartmentEntity(Long id,String departmentName, List <TeamEntity> teams) {
        this(id,departmentName);
        this.teams = teams;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List <TeamEntity> getTeams() {
        return teams;
    }

    public void setTeams(List <TeamEntity> teams) {
        this.teams = teams;
    }
}
