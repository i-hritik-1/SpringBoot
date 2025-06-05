package org.hritik.crudApp.mapper;

import org.hritik.crudApp.dto.EmployeeDto;
import org.hritik.crudApp.entity.Employee;

public class EmployeeMapper {
    public static EmployeeDto mapToEmployeeDto(Employee employee)
    {
        return new EmployeeDto(
                employee.getId(),
                employee.getFirst_name(),
                employee.getLast_name(),
                employee.getEmail()
        );
    }

    public static Employee mapToEmployee(EmployeeDto employeeDto)
    {
        return new Employee(
                null, // Assuming ID is auto-generated
                employeeDto.getFirst_name(),
                employeeDto.getLast_name(),
                employeeDto.getEmail()
        );
    }
}
