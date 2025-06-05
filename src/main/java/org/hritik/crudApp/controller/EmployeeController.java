package org.hritik.crudApp.controller;
import lombok.AllArgsConstructor;
import org.hritik.crudApp.dto.EmployeeDto;
import org.hritik.crudApp.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {
    private EmployeeService employeeService;

    ///  Add Employee REST API
    @PostMapping("/create")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto)
    {
        EmployeeDto savedEmployeeDto = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployeeDto, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeId(@PathVariable("id") Long employeeId)
    {
        EmployeeDto fetchedEmployeeData = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(fetchedEmployeeData);
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDto>> getAllEmployee()
    {
        List<EmployeeDto> employees = employeeService.getAllEmployee();

        return ResponseEntity.ok(employees);
    }

    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId,@RequestBody EmployeeDto updatedEmployee)
    {
        EmployeeDto employeeDto = employeeService.updateEmployee(employeeId,updatedEmployee);

        return ResponseEntity.ok(employeeDto);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<EmployeeDto> deleteEmployee(@PathVariable("id") Long employeeId)
    {
        EmployeeDto employeeDto =  employeeService.deleteEmployee(employeeId);

        return ResponseEntity.ok(employeeDto);
    }
}
