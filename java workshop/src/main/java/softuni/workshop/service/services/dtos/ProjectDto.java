package softuni.workshop.service.services.dtos;

import softuni.workshop.data.entities.Company;
import softuni.workshop.data.entities.Employee;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@XmlRootElement(name = "project")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProjectDto {


    private String name;
    private String description;
    @XmlElement(name = "is-finished")
    private boolean isFinished;
    private BigDecimal payment;
    @XmlElement(name = "start-date")
    private String startDate;
    private CompanyDto company;

    public ProjectDto() {
    }

    public String getName() {
        return name;
    }

    public ProjectDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProjectDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public ProjectDto setFinished(boolean finished) {
        isFinished = finished;
        return this;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public ProjectDto setPayment(BigDecimal payment) {
        this.payment = payment;
        return this;
    }

    public String getStartDate() {
        return startDate;
    }

    public ProjectDto setStartDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    public CompanyDto getCompany() {
        return company;
    }

    public ProjectDto setCompany(CompanyDto company) {
        this.company = company;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectDto)) return false;
        ProjectDto that = (ProjectDto) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(company, that.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, startDate, company);
    }

    @Override
    public String toString() {
        return "ProjectDto{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isFinished=" + isFinished +
                ", payment=" + payment +
                ", startDate='" + startDate + '\'' +
                ", company=" + company +
                '}';
    }
}
