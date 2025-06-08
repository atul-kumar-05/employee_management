package com.blog.example.employeemanagement.serviceImpl;

import com.blog.example.employeemanagement.exception.ResourceNotFoundException;
import com.blog.example.employeemanagement.model.Department;
import com.blog.example.employeemanagement.payload.DepartmentDto;
import com.blog.example.employeemanagement.payload.EmployeeDto;
import com.blog.example.employeemanagement.model.Employee;
import com.blog.example.employeemanagement.repository.DepartmentRepo;
import com.blog.example.employeemanagement.repository.EmployeeRepo;
import com.blog.example.employeemanagement.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto,Integer id) {
        Department department=this.departmentRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Department not found","id",id));
        Employee employee = this.modelMapper.map(employeeDto, Employee.class);
        employee.setDepartment(department);
        Employee savedEmployee = this.employeeRepo.save(employee);
        EmployeeDto response =this.modelMapper.map(savedEmployee,EmployeeDto.class);
        response.setDepartmentDto(this.modelMapper.map(department, DepartmentDto.class));
        return response;
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto, Integer employeeId) {
        Employee employee = this.employeeRepo.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee not found !!", "id", employeeId));
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employee.setPhone(employeeDto.getPhone());
        employee.setSalary(employeeDto.getSalary());
        Employee updatedEmployee = this.employeeRepo.save(employee);
        return modelMapper.map(updatedEmployee, EmployeeDto.class);
    }

    @Override
    public void deleteEmployee(Integer employeeId) {
        Employee employee = this.employeeRepo.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee not found !!", "id", employeeId));
        this.employeeRepo.delete(employee);
    }

    @Override
    public EmployeeDto findEmployeeById(Integer employeeId) {
        Employee employee = this.employeeRepo.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found !!", "id", employeeId));

        // Map employee to EmployeeDto
        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);

        // Manually map department → departmentDto
        if (employee.getDepartment() != null) {
            DepartmentDto departmentDto = modelMapper.map(employee.getDepartment(), DepartmentDto.class);
            employeeDto.setDepartmentDto(departmentDto);
        }

        return employeeDto;

    }

    @Override
    public List<EmployeeDto> findAllEmployee() {
        List<Employee> employees = this.employeeRepo.findAll();
        List<EmployeeDto> employeeDtos = new ArrayList<>();

        for (Employee employee : employees) {
            EmployeeDto dto = modelMapper.map(employee, EmployeeDto.class);

            if (employee.getDepartment() != null) {
                dto.setDepartmentDto(modelMapper.map(employee.getDepartment(), DepartmentDto.class));
            }

            employeeDtos.add(dto);
        }

        return employeeDtos;

    }



    @Override
    public List<EmployeeDto> getEmployeeByDepartment(Integer departmentId) {
        Department department=this.departmentRepo.findById(departmentId).orElseThrow(()->new ResourceNotFoundException("Department not found", "id", departmentId));
        List<Employee> employees = this.employeeRepo.findByDepartment(department);
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        employees.forEach(employee -> employeeDtos.add(modelMapper.map(employee, EmployeeDto.class)));
        return employeeDtos;
    }
}

