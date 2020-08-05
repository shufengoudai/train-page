package com.sun.japan.repository;

import com.sun.japan.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DepartmentRepository extends JpaRepository<Department,String> {
    @Query(value = "select department_id from department_master where department_name = ?1",nativeQuery = true)
    public String getDepartmentIdByDepartmentName(String departmentName);

    @Query(value = "select department_name from department_master where department_id = ?1",nativeQuery = true)
    public String getDepartmentNameByDepartmentId(String departmentId);
}
