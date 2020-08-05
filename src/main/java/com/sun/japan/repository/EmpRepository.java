package com.sun.japan.repository;

import com.sun.japan.entities.Emp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmpRepository extends JpaRepository<Emp,Integer> {
//    @Query("select * from employee_master where emp_name = ?1 and password = ?2")
    public Emp findAllByEmpNameAndPassword(String username,String password);

    @Query(value = "select emp_name from employee_master where emp_id = ?1",nativeQuery = true)
    public String getEmpNameByEmpId(Integer empId);

    @Query(value = "select emp_id from employee_master where emp_name = ?1 and department_id = ?2",nativeQuery = true)
    public Integer getEmpIdByEmpNameAndDepartmentId(String empName,String departmentId);

    public Emp getEmpByEmpId(Integer empId);

    @Query(value = "select emp_id from employee_master where department_id = ?1",nativeQuery = true)
    public List<Integer> getEmpIdByDepartmentId(String departmentId);

    @Query(value = "select emp_id from employee_master where emp_name = ?1 and password = ?2",nativeQuery = true)
    public Integer getEmpIdByEmpNameAndPassword(String empName,String password);

    @Query(value = "select emp_id from employee_master where emp_name = ?1",nativeQuery = true)
    public Integer getEmpIdByEmpName(String empName);
}
