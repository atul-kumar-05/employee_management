package com.blog.example.employeemanagement.payload;

import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    private Integer departmentId;
    @NotBlank(message = "Please provide Department name")
    private String departmentName;
    @NotBlank(message = "Please provide description")
    private String description;


}
