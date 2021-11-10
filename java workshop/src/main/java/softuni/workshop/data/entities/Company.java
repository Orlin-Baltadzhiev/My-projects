package softuni.workshop.data.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "companies")
public class Company extends BaseEntity{
    private String name;
    private Set<ProjectDto> projects;

    public Company(){
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }
    @OneToMany(mappedBy = "company")
    public Set<ProjectDto> getProjects() {
        return projects;
    }

    public void setProjects(Set<ProjectDto> projects) {
        this.projects = projects;

    }
}
