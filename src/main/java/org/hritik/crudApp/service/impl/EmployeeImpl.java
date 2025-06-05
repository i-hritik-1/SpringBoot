package org.hritik.crudApp.service.impl;

import lombok.AllArgsConstructor;
import org.hritik.crudApp.dto.EmployeeDto;
import org.hritik.crudApp.entity.Employee;
import org.hritik.crudApp.exception.ResourceNotFoundException;
import org.hritik.crudApp.mapper.EmployeeMapper;
import org.hritik.crudApp.repository.EmployeeRepository;
import org.hritik.crudApp.service.EmployeeService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class EmployeeImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto)
    {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId)
    {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow( () -> new ResourceNotFoundException("Employee is not exists with given id : " + employeeId) );

        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployee()
    {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(EmployeeMapper::mapToEmployeeDto)
                .collect(Collectors.toList());
    }


    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updateEmployee)
    {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () ->  new ResourceNotFoundException("Employee not found." + employeeId)
        );

        employee.setFirst_name(updateEmployee.getFirst_name());
        employee.setLast_name(updateEmployee.getLast_name());
        employee.setEmail(updateEmployee.getEmail());

        Employee updatedEmployeeObj = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }

    @Override
    public EmployeeDto deleteEmployee(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee not found." + employeeId)
        );

        employeeRepository.deleteById(employeeId);

        return EmployeeMapper.mapToEmployeeDto(employee);
    }
}
