package softuni.workshop.service.services;

import softuni.workshop.service.services.dtos.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    void importEmployees();

    boolean areImported();

    String readEmployeesXmlFile();

    String exportEmployeesWithAgeAboveText(int age);

    String exportEmployeesWithAgeAboveXml(int age);

    List<EmployeeDto> getEmployeeWithAgeAbove(int age);
}
