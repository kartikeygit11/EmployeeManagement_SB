package com.employee.employeeMangement.Controller;

import com.employee.employeeMangement.dto.EmployeeDto;
import com.employee.employeeMangement.service.EmployeeServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {
    private EmployeeServiceImpl employeeService;
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<EmployeeDto> createNewEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeService.createNewEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        List<EmployeeDto> allEmployees =  employeeService.getAllEmployees();
        return new ResponseEntity<>(allEmployees, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId){
        EmployeeDto employeeById = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(employeeById, HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<EmployeeDto> updateEmployeeById(@PathVariable("id") Long employeeId, @RequestBody EmployeeDto employeeDto){
        EmployeeDto employeeById = employeeService.updateEmployeeById(employeeId, employeeDto);
        return new ResponseEntity<>(employeeById, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") Long employeeId){
        employeeService.deleteEmployeeById(employeeId);
        return new ResponseEntity<>("Employee Record Deleted Successfully", HttpStatus.OK);
    }
}