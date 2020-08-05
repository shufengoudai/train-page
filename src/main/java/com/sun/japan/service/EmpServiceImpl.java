package com.sun.japan.service;

import com.sun.japan.entities.Emp;
import com.sun.japan.repository.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService{
    private EmpRepository empRepository;

    @Autowired
    public void setEmpRepository(EmpRepository empRepository) {
        this.empRepository = empRepository;
    }

    @Override
    public Emp loginIn(String username,String password) {
        return empRepository.findAllByEmpNameAndPassword(username,password);
    }

    @Override
    public String getEmpNameByEmpId(Integer empId) {
        return empRepository.getEmpNameByEmpId(empId);
    }

    @Override
    public Integer getEmpId(String empName, String departmentId) {
        return empRepository.getEmpIdByEmpNameAndDepartmentId(empName,departmentId);
    }

    @Override
    public Emp getEmp(Integer empId) {
        return empRepository.getEmpByEmpId(empId);
    }

    @Override
    public List<Integer> getEmpId(String departmentId) {
        return empRepository.getEmpIdByDepartmentId(departmentId);
    }

    @Override
    public Integer getEmpIdByUser(String empName, String password) {
        return empRepository.getEmpIdByEmpNameAndPassword(empName, password);
    }

    @Override
    public Integer getEmpIdByName(String empName) {
        return empRepository.getEmpIdByEmpName(empName);
    }
}
