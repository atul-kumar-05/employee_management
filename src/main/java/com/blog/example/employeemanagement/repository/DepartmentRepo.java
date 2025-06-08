package com.blog.example.employeemanagement.repository;

import com.blog.example.employeemanagement.model.Department;
import com.blog.example.employeemanagement.payload.EmployeeDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepo extends JpaRepository<Department, Integer> {

}

