package com.blog.example.employeemanagement.service;

import com.blog.example.employeemanagement.payload.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    public DepartmentDto createDepartment(DepartmentDto departmentDto);
    public DepartmentDto updateDepartment(DepartmentDto departmentDto,Integer departmentId);
    public DepartmentDto getDepartmentById(Integer departmentId);
    public List<DepartmentDto> getAllDepartments();
    public void deleteDepartment(Integer departmentId);
}
