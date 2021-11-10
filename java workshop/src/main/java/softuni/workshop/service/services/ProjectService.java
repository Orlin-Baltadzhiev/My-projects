package softuni.workshop.service.services;

import softuni.workshop.service.services.dtos.ProjectDto;

import java.util.List;

public interface ProjectService {

    void importProjects();

    boolean areImported();

    String readProjectsXmlFile();

    String exportFinishedProjectsAsXml();

    List<ProjectDto> getFinishedProjects();

    String exportFinishedProjectsText();
}
