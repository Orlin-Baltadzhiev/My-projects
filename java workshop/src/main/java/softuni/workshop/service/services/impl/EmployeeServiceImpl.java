package softuni.workshop.service.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.workshop.data.entities.Company;
import softuni.workshop.data.entities.Employee;
import softuni.workshop.data.entities.ProjectDto;
import softuni.workshop.data.repositories.CompanyRepository;
import softuni.workshop.data.repositories.EmployeeRepository;
import softuni.workshop.data.repositories.ProjectRepository;
import softuni.workshop.exceptions.CustomXmlException;
import softuni.workshop.exceptions.EntityNotFoundException;
import softuni.workshop.service.services.EmployeeService;
import softuni.workshop.service.services.dtos.EmployeeDto;
import softuni.workshop.service.services.dtos.EmployeeRootDto;
import softuni.workshop.util.XmlParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final String EMPLOYEE_PATH = "src/main/resources/files/xmls/employees.xml";

    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;
    private final ProjectRepository projectRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, CompanyRepository companyRepository, ProjectRepository projectRepository, XmlParser xmlParser, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
        this.projectRepository = projectRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
    }

    @Override
    public void importEmployees() {
        EmployeeRootDto employeeRootDto = xmlParser.parseXml(EmployeeRootDto.class,EMPLOYEE_PATH);

        for (EmployeeDto employeeDto : employeeRootDto.getEmployeeDtoList()) {
            Employee employee = modelMapper.map(employeeDto, Employee.class);
            Company company =companyRepository.findByName(employee.getProject().getCompany().getName()).orElseThrow(
                    () -> new EntityNotFoundException(
                            String.format("Company '%s' not found.", employee.getProject().getCompany().getName())
                    )
            );
            ProjectDto project = projectRepository.findByNameAndCompanyAndStartDate(
                    employee.getProject().getName(),
                    company,
                    employee.getProject().getStartDate())
                    .orElseThrow(
                            () -> new EntityNotFoundException(String.format("Project '%s' not found.", employee.getProject().getName())
                    )
            );
            employee.setProject(project);
            this.employeeRepository.saveAndFlush(employee);
        }
    }

    @Override
    public boolean areImported() {
        return employeeRepository.count() > 0;
    }

    @Override
    public String readEmployeesXmlFile() {
        try {
            return String.join("\n", Files.readAllLines(Path.of(EMPLOYEE_PATH)));
        } catch (IOException e) {
            throw new CustomXmlException(e.getMessage(),e);
        }
    }

    @Override
    public String exportEmployeesWithAgeAboveText(int age) {
        StringBuilder sb = new StringBuilder();
        getEmployeeWithAgeAbove(age).forEach(e ->
                sb.append("Name: ").append(e.getFirstName())
                        .append(" ").append(e.getLastName())
                        .append("\n   Age: ").append(e.getAge())
                        .append("\n   Project Name: ").append(e.getProject().getName())
                        .append("\n"));
        return sb.toString();
    }

    @Override
    public String exportEmployeesWithAgeAboveXml(int age) {
        return xmlParser.exportXml(new EmployeeRootDto(getEmployeeWithAgeAbove(age)),EmployeeRootDto.class);
    }

    @Override
    public List<EmployeeDto> getEmployeeWithAgeAbove(int age) {
        List<Employee> employees = employeeRepository.findAllByAgeGreaterThan(25);
        return  employees.stream()
                .map(employee -> modelMapper.map(employee, EmployeeDto.class))
                .collect(Collectors.toList());
    }
}













