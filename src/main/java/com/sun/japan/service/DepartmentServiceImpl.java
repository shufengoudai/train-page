package com.sun.japan.service;

import com.sun.japan.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService{
    private DepartmentRepository departmentRepository;

    @Autowired
    public void setDepartmentRepository(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public String getDepartmentId(String departmentName) {
        return departmentRepository.getDepartmentIdByDepartmentName(departmentName);
    }

    @Override
    public String getDepartmentName(String departmentId) {
        return departmentRepository.getDepartmentNameByDepartmentId(departmentId);
    }
}
