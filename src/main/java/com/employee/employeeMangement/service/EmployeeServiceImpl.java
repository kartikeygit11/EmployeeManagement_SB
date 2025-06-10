package com.employee.employeeMangement.service;

import com.employee.employeeMangement.Entity.Employee;
import com.employee.employeeMangement.Exception.EmployeeResourceNotFoundException;
import com.employee.employeeMangement.Mapper.EmployeeMapper;
import com.employee.employeeMangement.Repository.EmployeeRepository;
import com.employee.employeeMangement.dto.EmployeeDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;


    @Override
    public EmployeeDto createNewEmployee(EmployeeDto employeeDto){
        Employee newEmployee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(newEmployee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> allEmployees = employeeRepository.findAll();
        return allEmployees.stream().map((e) -> EmployeeMapper.mapToEmployeeDto(e)).collect(Collectors.toList());
    }

    @Override
    public  EmployeeDto getEmployeeById(Long id){
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeResourceNotFoundException("Employee Id requested doesn't exist..."));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public EmployeeDto updateEmployeeById(Long id, EmployeeDto employeeDto){
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeResourceNotFoundException("Employee Id requested doesn't exist ..."));
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employee.setDepartment(employeeDto.getDepartment());
        employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public void deleteEmployeeById(Long id){
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new EmployeeResourceNotFoundException("No ID"));
        employeeRepository.deleteById(id);
    }
}