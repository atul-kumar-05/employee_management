package com.blog.example.employeemanagement.serviceImpl;

import com.blog.example.employeemanagement.exception.ResourceNotFoundException;
import com.blog.example.employeemanagement.payload.DepartmentDto;
import com.blog.example.employeemanagement.model.Department;
import com.blog.example.employeemanagement.repository.DepartmentRepo;
import com.blog.example.employeemanagement.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Department department = modelMapper.map(departmentDto, Department.class);
        Department department1=departmentRepo.save(department);
        return modelMapper.map(department1, DepartmentDto.class);
    }

    @Override
    public DepartmentDto updateDepartment(DepartmentDto departmentDto,Integer departmentId) {
        Department department = departmentRepo.findById(departmentId).orElseThrow(()->new ResourceNotFoundException("Department not found", "id", departmentId));
        department.setDepartmentName(departmentDto.getDepartmentName());
        department.setDescription(departmentDto.getDescription());
        Department department1=departmentRepo.save(department);
        return modelMapper.map(department1, DepartmentDto.class);
    }

    @Override
    public DepartmentDto getDepartmentById(Integer departmentId) {
        Department department=departmentRepo.findById(departmentId).orElseThrow(()->new ResourceNotFoundException("Department not found", "id", departmentId));

        return modelMapper.map(department, DepartmentDto.class);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments=departmentRepo.findAll();
        List<DepartmentDto> departmentDtos=new ArrayList<>();
        departments.forEach(department -> departmentDtos.add(modelMapper.map(department, DepartmentDto.class)));
        return departmentDtos;
    }

    @Override
    public void deleteDepartment(Integer departmentId) {
        departmentRepo.deleteById(departmentId);

    }

}
