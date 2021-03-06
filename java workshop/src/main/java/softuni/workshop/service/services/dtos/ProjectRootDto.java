package softuni.workshop.service.services.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "projects")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProjectRootDto {

    @XmlElement(name = "project")
    private List<ProjectDto> projectDtoList;

    public ProjectRootDto() {
    }

    public ProjectRootDto(List<ProjectDto> projectDtoList) {
        this.projectDtoList = projectDtoList;
    }

    public List<ProjectDto> getProjectDtoList() {
        return projectDtoList;
    }

    public ProjectRootDto setProjectDtoList(List<ProjectDto> projectDtoList) {
        this.projectDtoList = projectDtoList;
        return this;
    }
}
