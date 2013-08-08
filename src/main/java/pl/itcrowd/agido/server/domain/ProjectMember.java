package pl.itcrowd.agido.server.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@MappedSuperclass
public abstract class ProjectMember implements Serializable {

    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "PROJECT_ID", nullable = false)
    protected Project project;

    public Project getProject()
    {
        return project;
    }

    public void setProject(Project project)
    {
        this.project = project;
    }
}
