package com.sun.japan.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "department_master")
public class Department implements Serializable{
    @Id
    private String departmentId;

    private String departmentName;

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
