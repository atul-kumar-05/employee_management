package com.blog.example.employeemanagement.service;

import com.blog.example.employeemanagement.model.Department;
import com.blog.example.employeemanagement.payload.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    public EmployeeDto createEmployee(EmployeeDto employeeDto,Integer id);
    public EmployeeDto updateEmployee(EmployeeDto employeeDto,Integer employeeId);
    public void deleteEmployee(Integer employeeId);
    public EmployeeDto findEmployeeById(Integer employeeId);
    public List<EmployeeDto> findAllEmployee();
    List<EmployeeDto> getEmployeeByDepartment(Integer departmentId);
}
