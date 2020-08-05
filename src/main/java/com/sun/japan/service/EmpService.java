package com.sun.japan.service;

import com.sun.japan.entities.Emp;

import java.util.List;

public interface EmpService {
    public Emp loginIn(String username,String password);

    public String getEmpNameByEmpId(Integer empId);

    public Integer getEmpId(String empName,String departmentId);

    public Emp getEmp(Integer empId);

    public List<Integer> getEmpId(String departmentId);

    public Integer getEmpIdByUser(String empName,String password);

    public Integer getEmpIdByName(String empName);
}
